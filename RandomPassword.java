import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;

public class RandomPassword
{
    // Constant variables
    private static final String    FLAG_ALPHA_PW  = "-c";
    private static final String FLAG_NUM_ONLY_PW  = "-n";
    private static final String  FLAG_PRINT_LAST  = "-p";
    private static final String   FLAG_PRINT_ALL  = "-a";
    private static final String  FLAG_PRINT_FILE  = "-f";
    private static final String  FLAG_DELETE_ALL  = "-D";
    private static final String     FLAG_PW_SIZE  = "-s";
    private static final String        FLAG_HELP  = "-h";
    private static final    int  DEFAULT_PW_SIZE  = 8;
    private static final String PW_DATABASE_FILE  = ".pwcollection.txt";
    private static final String    PW_PRINT_FILE  = "SavedPasswords.txt";

    // Instance variables of password generating object
    private           boolean   onlyNumeric;
    private               int        pwSize = -1;
    private ArrayList<String> argsArrayList;

    public RandomPassword(String[] cmdArgs)
    {
        try
        {
            FileReader existingFile = new FileReader(PW_DATABASE_FILE);
        }
        catch (FileNotFoundException ex)
        {
            try
            {
                PrintWriter filecreator = new PrintWriter(new FileWriter(PW_DATABASE_FILE));
                filecreator.println();
            }
            catch (IOException ioex)
            {
                System.err.println("Database could not be created. Possible permission error.");
            }
        }
        // Transfer command-line arguments for easy ArrayList functions
        argsArrayList = new ArrayList<String>();
        for (int i = 0; i < cmdArgs.length; ++i)
            argsArrayList.add(cmdArgs[i]);

        // Disregarding all other flags, just print the info guide
        if (argsArrayList.contains(FLAG_HELP))
        {
            System.out.println(printDirections());
            System.exit(0);
        } // End print -h

        // Disregarding all other flags, clear the database
        if (argsArrayList.contains(FLAG_DELETE_ALL))
        {
            if (deleteDatabasePrompt())
            {
                try
                {
                    PrintWriter overwriteFile = new PrintWriter(new java.io.FileWriter(PW_DATABASE_FILE));
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
        } // End delete database -D

        // Helper method for handling general improper arguments
        if (!parsableArguments(argsArrayList))
        {
            System.err.println("\nCould not parse argument(s). Restart the program.\n"
            + "\tAdd \"-h\" as an argument for directions.");
            System.exit(1);
        } // end !parsableArguments()

        if (argsArrayList.contains(FLAG_ALPHA_PW))
            onlyNumeric = false;
        if (argsArrayList.contains(FLAG_NUM_ONLY_PW))
            onlyNumeric = true;
        if (!argsArrayList.contains(FLAG_PW_SIZE))
            pwSize = DEFAULT_PW_SIZE;
        else
        {
            int sizeIndex = argsArrayList.indexOf(FLAG_PW_SIZE);
            try
            {
                pwSize = Integer.parseInt(argsArrayList.get(sizeIndex + 1));
                if (pwSize < 1)
                    throw new IndexOutOfBoundsException();
            }
            catch (NumberFormatException ex)
            {
                System.err.println("\n\nCould not properly parse size argument. Restart program.\n"
                + "\tAdd \"-h\" as an argument for directions.");
                System.exit(1);
            }
            catch (IndexOutOfBoundsException ex)
            {
                System.err.println("\n\nCould not properly parse size argument. Restart program.\n"
                + "\tProvide \"-h\" as an argument for directions.");
                System.exit(1);
            } // End Try-Catch
        } // End list-contains Size
    } // Constructor

    private boolean parsableArguments(ArrayList<String> argsArrayList)
    {
        if (argsArrayList.contains(FLAG_ALPHA_PW) &&
            argsArrayList.contains(FLAG_NUM_ONLY_PW))
            return false;

        if ((argsArrayList.contains(FLAG_ALPHA_PW) ||
             argsArrayList.contains(FLAG_NUM_ONLY_PW)) &&
            (argsArrayList.contains(FLAG_PRINT_LAST) ||
             argsArrayList.contains(FLAG_PRINT_ALL)))
             return false;

        if (argsArrayList.contains(FLAG_PRINT_LAST) &&
            argsArrayList.contains(FLAG_PRINT_ALL))
            return false;

        if (argsArrayList.contains(FLAG_PW_SIZE) &&
          !(argsArrayList.contains(FLAG_ALPHA_PW) ||
            argsArrayList.contains(FLAG_NUM_ONLY_PW)))
            return false;

        for (String args : argsArrayList)
        {
            if (args.equals("-"))
                return false;
            if (args.length() > 2)
                return false;
            if (args.charAt(0) != '-')
            {
                if (!argsArrayList.contains(FLAG_PW_SIZE))
                    return false;
                try
                {
                    // Rare circumstance, copy into new array, remove element, recheck array
                    ArrayList<String> argsCopyList =  new ArrayList<String>(argsArrayList);
                    int indexOfSize = argsArrayList.indexOf(FLAG_PW_SIZE) + 1;
                    argsCopyList.remove(indexOfSize);
                    for (String copyArgs : argsCopyList)
                        if (copyArgs.charAt(0) != '-')
                            return false;
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
    }

    private boolean deleteDatabasePrompt()
    {
        // User opts to delete their password history. Make them enter a confirmation number
        int randConfirmation = new Random().nextInt(89999) + 1001;

        System.out.print("\n\n\t || WARNING! ||\n\nYou have optioned to delete the entire database!\n"
        + "Please enter the code provided to confirm your selection."
        + "\n\n\tConfirmation Code: " + randConfirmation + "\n"
        + "Enter the confirmation Code: ");
        String confirmedInput = new Scanner(System.in).next();

        try
        {
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
    }

    private void setAsOnlyNumeric(boolean value)
    {
        onlyNumeric = value;
    } // End setAsOnlyNumeric()

    public boolean getAsOnlyNumeric()
    {
        return onlyNumeric;
    }

    public int getPWSize()
    {
        return pwSize;
    }

    /* The real logic of the program. */
    private static int getRandomChar()
    {
        boolean notValid = true;
        int number = -1;
        while (notValid)
        {
            number = new Random().nextInt(74) + 48;
            if (!(number >= 58 && number <= 65) && !(number >= 91 && number <= 96))
                notValid = false;
        }
        return number;
    } // End int getRandomChar()

    public static String generatePW(RandomPassword pwObj)
    {
        // Loop generating random letter and/or numbers, concatenate with complete string
        String completedPassword = "";
        if (pwObj.getAsOnlyNumeric())
        {
            for (int i = 0; i < pwObj.getPWSize(); ++i)
            {
                int number = new Random().nextInt(10);
                completedPassword += number;
            }
            return completedPassword;
        }
        for (int i = 0; i < pwObj.getPWSize(); ++i)
        {
            char letter = 'A' - 65; // Initial letter
            letter += getRandomChar();
            completedPassword += letter;
        }
        return completedPassword;
    }

    public static String printDirections()
    {
        return "Eric McDaniel's Random Password Generator\n\n"
            + "Purpose  ---\tThis program generates a random password\n"
            + "\t\tbased on the command line flag provided.\n"
            + "\t\tThe passwords can either be alpha-numeric or\n"
            + "\t\tnumeric only, are customizable in length, and\n"
            + "\t\twill be stored into memory for future retrieval.\n\n\n"
            + "Usage:\n"
            + "  " + FLAG_ALPHA_PW    + "\t\tRandom password containing a mixture of\n (or..)\t\tcharacters and numbers.\n"
            + "  " + FLAG_NUM_ONLY_PW + "\t\tRandom password containing numbers only.\n\n"
            + "  " + FLAG_PRINT_LAST  + "\t\tPrint the most recent password.\n"
            + "  " + FLAG_PRINT_ALL   + "\t\tPrint ALL of the timestamped passwords.\n"
            + "  " + FLAG_PRINT_FILE  + "\t\tRedirect ALL timestamped passwords to\n"
            + "\t\ta textfile named \"SavedPasswords.txt\"\n\n\n"
            + "Optional:\n"
            + "  " + FLAG_PW_SIZE + " <VALUE>\tCustomize the password's length. The default\n"
            + "\t\tsize of " + DEFAULT_PW_SIZE + " numbers and/or characters will\n"
            + "\t\totherwise be used.\n"
            + "  " + FLAG_DELETE_ALL + "\t\tClear entire database\n\n"
            + "  " + FLAG_HELP + "\t\tPrint this help dialog.\n\n";
    } // End String printDirections()

    public static String printPasswords(RandomPassword pwObj)
    {
        Scanner fileReadingObj = null;
        try
        {
            fileReadingObj = new Scanner(new FileReader(PW_DATABASE_FILE));
            if (!fileReadingObj.hasNext())
            {
                System.err.println("No password records were found in the database.");
                System.exit(1);
            }
            
            String fullDatabase = "";
            if (pwObj.argsArrayList.contains(FLAG_PRINT_ALL) || pwObj.argsArrayList.contains(FLAG_PRINT_FILE))
            {
                while (fileReadingObj.hasNext())
                {
                    fullDatabase += "[Password generated on " + fileReadingObj.next()
                    + "/" + fileReadingObj.next() + "/" + fileReadingObj.next() + ", at "
                    + fileReadingObj.next() + ":" + fileReadingObj.next() + ":"
                    + fileReadingObj.next() + " " + fileReadingObj.next() + "]\n\t"
                    + fileReadingObj.next() + "\n\n";
                }
                return fullDatabase;
            } // End if contains -a
            if (pwObj.argsArrayList.contains(FLAG_PRINT_LAST))
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
            + "of a file permission error.\n\n\tGenerate a new password to troubleshoot.\n");
            System.exit(1);
        }
        finally
        {
            if (fileReadingObj != null)
                fileReadingObj.close();
        }
        return null; // Should never encounter
    } // End String printPasswords()

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Improper command line argument(s) provided.\n\n"
            + printDirections());
            System.exit(1);
        }
        PrintWriter outWriter = null;
        PrintWriter printFile = null;
        try
        {
            RandomPassword pwObj = new RandomPassword(args);

            if (pwObj.argsArrayList.contains(FLAG_PRINT_FILE))
            {
                printFile = new PrintWriter(new FileWriter(PW_PRINT_FILE));
                printFile.print(printPasswords(pwObj));
                System.out.println("Passwords have successfully been printed on to:\n"
                + "\t\"SavedPasswords.txt\"");
                printFile.close();
                System.exit(0);
            }

            if (pwObj.argsArrayList.contains(FLAG_PRINT_ALL) ||
                pwObj.argsArrayList.contains(FLAG_PRINT_LAST))
            {
                System.out.print(printPasswords(pwObj));
                System.exit(0);
            }

            // Instantiate io objects
            outWriter = new PrintWriter(new FileWriter(PW_DATABASE_FILE, true));
            String completedPassword = generatePW(pwObj);


            // Use Date and DateFormat objects to make formatted timestamps
            java.util.Date date = new java.util.Date();
            String strDateFormatToScreen = "MM/dd/yy, hh:mm:ss a";
            java.text.DateFormat dateFormatToScreen = new java.text.SimpleDateFormat(strDateFormatToScreen);
            String formattedDateToScreen = dateFormatToScreen.format(date);
            String strDateFormatToFile = "MM dd yy hh mm ss a";
            java.text.DateFormat dateFormatToFile = new java.text.SimpleDateFormat(strDateFormatToFile);
            String formattedDateToFile = dateFormatToFile.format(date);

            // Print to the screen with formatted time, append to the database file
            System.out.println("[Password generated on " + formattedDateToScreen + "]");
            System.out.println("\t" + completedPassword);
            outWriter.println(formattedDateToFile + " " + completedPassword);
        }
        catch (InputMismatchException ex)
        {
            System.err.println("Cannot recognize input as an integer. Restart program.\n"
            + "\tAdd \"-h\" as an argument for directions.");
        }
        catch (IOException ex)
        {
            System.err.println("Error reading or writing to file. Program terminated.");
        }
        finally
        {
            if (outWriter != null)
                outWriter.close();
            if (printFile != null)
                printFile.close();
        } // End try-catch block
    } // End main()
} // End class RandomPassword
