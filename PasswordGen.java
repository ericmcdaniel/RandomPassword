import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
/**
 * <p>This is a helper class of the Random Password generator. This program was written
 * to help combat procrastination by providing a random password to temporarily change
 * the password of a social media account. This particular class allows the creation
 * of a password-generating object, providing methods for its configuation.</p>
 *
 * @author Eric McDaniel
 * @version 1.3
 */
public class PasswordGen
{
    // Instance variables of the password generating object
    private             boolean   onlyNumeric;
    private                 int        pwSize = -1;
    protected ArrayList<String> argsArrayList;

    /**
     * <p>Constructor for the PasswordGen class. Creates a new password generating object
     * with functions to customize how passwords are created.</p>
     *
     * @param cmdArgs The arguments provided by the user at execution
     */
    public PasswordGen(String[] cmdArgs)
    {
        // Check to see if a database of PWs exists. Create one if none found. 
        try
        {
            FileReader existingFile = new FileReader(RandomPassword.PW_DATABASE_FILE);
        }
        catch (FileNotFoundException ex)
        {
            PrintWriter filecreator = null;
            try
            {
                filecreator = new PrintWriter(new FileWriter(RandomPassword.PW_DATABASE_FILE));
                filecreator.println();
            }
            catch (IOException ioex)
            {
                System.err.println("Database could not be created. Possible permission error.\n"
                                 + "Modify source code and rename the value in the constant String"
                                 + "\"PW_DATABASE_FILE\". Otherwise, contact the developer.");
                System.exit(1);
            }
            finally
            {
                if (filecreator != null)
                    filecreator.close();
            }
        } // End try-catch DB file exists

        // Transfer command-line arguments for public ArrayList functions
        argsArrayList = new ArrayList<String>();
        for (int i = 0; i < cmdArgs.length; ++i)
            argsArrayList.add(cmdArgs[i]);

        // Disregarding all other flags, just print the info guide
        if (argsArrayList.contains(RandomPassword.FLAG_HELP))
        {
            System.out.println(RandomPassword.printDirections());
            System.exit(0);
        } // End print -h

        // Disregarding all other flags, clear the database
        if (argsArrayList.contains(RandomPassword.FLAG_DELETE_ALL))
        {
            deleteDatabase();
        } // End delete database -D

        // Helper method for handling general improper arguments
        if (!parsableArguments())
        {
            System.err.println("\nCould not parse argument(s). Restart the program.\n"
                             + "\tAdd \"-h\" as an argument for directions.");
            System.exit(1);
        } // end !parsableArguments()

        if (argsArrayList.contains(RandomPassword.FLAG_ALPHA_PW))
            onlyNumeric = false;
        if (argsArrayList.contains(RandomPassword.FLAG_NUM_ONLY_PW))
            onlyNumeric = true;
        if (!argsArrayList.contains(RandomPassword.FLAG_PW_SIZE))
            pwSize = RandomPassword.DEFAULT_PW_SIZE; // User didn't provide length. Default to 8
        else
        {
            int sizeIndex = argsArrayList.indexOf(RandomPassword.FLAG_PW_SIZE);
            try
            {
                // size comes after the "-s" adding 1 to the indexOf size
                pwSize = Integer.parseInt(argsArrayList.get(sizeIndex + 1));
                if (pwSize < 1)
                    throw new IndexOutOfBoundsException();
            }
            catch (NumberFormatException ex)
            {
                System.err.println("\nCould not properly parse size argument. Restart program.\n"
                                 + "\tAdd \"-h\" as an argument for directions.");
                System.exit(1);
            }
            catch (IndexOutOfBoundsException ex)
            {
                System.err.println("\nCould not properly parse size argument. Restart program.\n"
                                 + "\tProvide \"-h\" as an argument for directions.");
                System.exit(1);
            } // End Try-Catch
        } // End list-contains Size
    } // Constructor

    /**
     * <p>Evaluates the arguments provided by the user to determine if the program
     * should continue with execution. The program will halt if an argument
     * cannot be parsed.</p>
     *
     * @return True if the user provided parsable arguments.
     */
    private boolean parsableArguments()
    {
        // Individual cases which should halt the program. Somewhat arbitrary....
        if (argsArrayList.contains(RandomPassword.FLAG_ALPHA_PW) &&
            argsArrayList.contains(RandomPassword.FLAG_NUM_ONLY_PW))
            return false;

        if ((argsArrayList.contains(RandomPassword.FLAG_ALPHA_PW) ||
             argsArrayList.contains(RandomPassword.FLAG_NUM_ONLY_PW)) &&
            (argsArrayList.contains(RandomPassword.FLAG_PRINT_LAST) ||
             argsArrayList.contains(RandomPassword.FLAG_PRINT_ALL)))
             return false;

        if (argsArrayList.contains(RandomPassword.FLAG_PRINT_LAST) &&
            argsArrayList.contains(RandomPassword.FLAG_PRINT_ALL))
            return false;

        if (argsArrayList.contains(RandomPassword.FLAG_PW_SIZE) &&
          !(argsArrayList.contains(RandomPassword.FLAG_ALPHA_PW) ||
            argsArrayList.contains(RandomPassword.FLAG_NUM_ONLY_PW)))
            return false;

        // Final parsing, based on individual tokens
        for (String args : argsArrayList)
        {
            if (args.equals("-"))
                return false;
            if (args.length() > 2)
                return false;
            if (args.charAt(0) != '-')
            {
                if (!argsArrayList.contains(RandomPassword.FLAG_PW_SIZE))
                    return false;
                try
                {
                    // Rare circumstance, copy into new array, remove element, recheck array
                    ArrayList<String> argsCopyList =  new ArrayList<String>(argsArrayList);
                    int indexOfSize = argsArrayList.indexOf(RandomPassword.FLAG_PW_SIZE) + 1;
                    argsCopyList.remove(indexOfSize);
                    for (String copyArgs : argsCopyList)
                        if (copyArgs.charAt(0) != '-')
                            return false;       // multiple args without "-" was provided
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("\nCould not properly parse size argument. Restart program.\n"
                                     + "\tProvide \"-h\" as an argument for directions.");
                    System.exit(1);
                } // End Try-Catch
            } // End charAt(0) != -
        } // End for ArrayList loop
        return true;
    } // End boolean parsableArguments()

    /**
     * <p>WARNING: THIS FUNCTION CONTAINS SIDE EFFECTS. Prints to STDOUT.</p>
     * <p>Provides the user with a numerical prompt to delete their password
     * database. If the user input matches the randomly generated number, the
     * boolean value true will be return to execute the calling method.</p>
     *
     * @return True if the user provided integer matches the randomly
     * generated confirmation number
     */
    private boolean deleteDatabasePrompt()
    {
        // User opts to delete their password history. Make them enter a confirmation number
        int randConfirmation = new Random().nextInt(89999) + 1001;

        System.out.print("\n\t || WARNING! ||\n\nYou have optioned to delete the entire database!\n"
                       + "Please enter the code provided to confirm your selection."
                       + "\n\n\tConfirmation Code: " + randConfirmation + "\n"
                       + "Enter the confirmation code: ");
        String confirmedInput = new Scanner(System.in).next();

        try
        {
            // Compare user input with randomly generated number
            int parsedConfirmInput = Integer.parseInt(confirmedInput);
            if (parsedConfirmInput == randConfirmation)
                return true;
            return false;
        }
        catch (NumberFormatException ex)
        {
            System.err.println("\nCould not confirm request.\nInvalid input.");
        }
        return false;
    } // End boolean deleteDatabasePrompt()

    /**
     * <p>WARNING: THIS FUNCTION CONTAINS SIDE EFFECTS. Prints to STDOUT.</p>
     * <p>This method is called from the constructor if the user wishes to delete
     * their database by adding the -f flag.</p>
     */
    private void deleteDatabase()
    {
        if (deleteDatabasePrompt())
        {
            try
            {
                PrintWriter overwriteFile = new PrintWriter(new FileWriter(RandomPassword.PW_DATABASE_FILE));
                overwriteFile.println();
                System.out.println("\nDatabase successfully cleared.");
                System.exit(0);
            }
            catch (IOException ex)
            {
                System.err.println("\nDatabase delete operation was unsuccessful.\n"
                                 + "There possibly was no file to begin with.");
            } // End try-catch
        } // End if user prompts successfully
        else
            System.err.println("\nDatabase delete operation was unsuccessful.\n"
                             + "Incorrect confirmation code provided.");
        System.exit(1);
    } // End void deleteDatabase()

    /**
     * 
     * <p>Changes the object if password should contain characters or numbers only.</p>
     *
     * @param value The new boolean value
     */
    private void setAsOnlyNumeric(boolean value)
    {
        onlyNumeric = value;
    } // End setAsOnlyNumeric()

    /**
     * <p>Determines if the password will contain characters or numbers only.</p>
     *
     * @return True if password is only numeric, False if characters and numbers
     */
    public boolean getAsOnlyNumeric()
    {
        return onlyNumeric;
    } // End boolean getAsOnlyNumeric()

    /**
     * <p>Gets the size of the password. The defaut is 8 characters if the user
     * does not make a the modification.</p>
     *
     * @return pwSize The length of the password
     */
    public int getPWSize()
    {
        return pwSize;
    } // end int getPWSize()

    /**
     * <p>Provides the string represention of the passwords, depending on which
     * flag the user provided when the program was first executed. The passwords
     * are neatly formatted and decorated.</p>
     *
     * @return fullDatabase The String containing all passwords
     */
    public String printPasswords()
    {
        Scanner fileReadingObj = null;
        try
        {
            fileReadingObj = new Scanner(new FileReader(RandomPassword.PW_DATABASE_FILE));
            if (!fileReadingObj.hasNext())
            {
                System.err.println("No password records were found in the database.");
                System.exit(1);
            }

            String fullDatabase = "";
            if (argsArrayList.contains(RandomPassword.FLAG_PRINT_ALL) ||
                argsArrayList.contains(RandomPassword.FLAG_PRINT_FILE))
            {
                while (fileReadingObj.hasNextLine())
                {
                    String databaseEntry = fileReadingObj.nextLine();
                    Scanner lineTokenReader = new Scanner(databaseEntry);

                    if (!fileReadingObj.hasNextLine())
                        fullDatabase += ".--------------------------------------------------"
                                      + "-------------.\n| [Most recent password, generated "
                                      + "on " + lineTokenReader.next() + "/"
                                      + lineTokenReader.next() + "/" + lineTokenReader.next()
                                      + ", at " + lineTokenReader.next() + ":"
                                      +lineTokenReader.next() + ":" + lineTokenReader.next() 
                                      + " " + lineTokenReader.next() + "]\n|\t" 
                                      + lineTokenReader.next() + " \n.------------------"
                                      + "---------------------------------------------.\n";
                    else
                        fullDatabase += "[Password previously generated on " + lineTokenReader.next()
                                      + "/" + lineTokenReader.next() + "/" + lineTokenReader.next() 
                                      + ", at " + lineTokenReader.next() + ":" + lineTokenReader.next() 
                                      + ":" + lineTokenReader.next() + " " + lineTokenReader.next() 
                                      + "]\n\t" + lineTokenReader.next() + "\n\n";
                }
                return fullDatabase;
            } // End if contains -a
            if (argsArrayList.contains(RandomPassword.FLAG_PRINT_LAST))
            {
                String finalLine = "";
                while (fileReadingObj.hasNextLine())
                {
                    finalLine = fileReadingObj.nextLine();
                }
                Scanner token = new Scanner(finalLine);
                while (token.hasNext())
                {
                    fullDatabase += "[Last password generated on " + token.next()
                                  + "/" + token.next() + "/" + token.next() + ", at "
                                  + token.next() + ":" + token.next() + ":"
                                  + token.next() + " " + token.next() + "]\n\t"
                                  + token.next() + "\n\n";
                }
                return fullDatabase;
            } // End if contains -p
        } // End try block
        catch (FileNotFoundException ex)
        {
            System.err.println("Error loading password database.\nThis likely was the result "
                             + "of a file permission error.\n"
                             + "\tGenerate a new password to troubleshoot.\n");
            System.exit(1);
        }
        finally
        {
            if (fileReadingObj != null)
                fileReadingObj.close();
        }
        return null; // Should never encounter
    } // End String printPasswords()

    /** 
     * <p>The real logic of the program. Uses the ASCII values associated with each
     * character to generate the random characters. Refer to the ASCII table on 
     * <a href="http://ee.hawaii.edu/~tep/EE160/Book/chap4/subsection2.1.1.1.html">this
     * website for details.</a></p>
     *
     * @return number The ASCII number that is to be used as the ASCII char.
     */
    private static int getRandomChar()
    {
        boolean valid = false;
        int number = -1;
        while (!valid)
        {
            number = new Random().nextInt(74) + 48;
            if (!(number > 57 && number < 65) && !(number > 90 && number < 97))
                valid = true;
        }
        return number;
    } // End int getRandomChar()

    /**
     * <p>The real logic of the program. Iterates through the length of the password
     * to provide random characters. Uses ASCII encoding.</p>
     *
     * @return completedPassword The final random password product
     */
    public String generatePW()
    {
        // Loop generating random letter and/or numbers, concatenate with complete string
        String completedPassword = "";

        // If the user only wants numbers
        if (getAsOnlyNumeric())
        {
            for (int i = 0; i < getPWSize(); ++i)
            {
                int number = new Random().nextInt(10); // Generates 0 - 9 for each digit
                completedPassword += number;
            }
            return completedPassword;
        }

        // If the user wants characters and numbers
        for (int i = 0; i < getPWSize(); ++i)
        {
            char letter = 'A' - 65; // Initial letter
            letter += getRandomChar();
            completedPassword += letter;
        }
        return completedPassword;
    } // End String generatePW
} // End class PasswordGen