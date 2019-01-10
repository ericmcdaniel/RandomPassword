import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
/**
 * <p>This is the main class of the Random Password generator. This program was written
 * to help combat procrastination by providing a random password to temporarily change
 * the password of a social media account.</p>
 *
 * @author Eric McDaniel
 * @version 1.3
 */
public class RandomPassword
{
    // Constant static variables
    public static final String    FLAG_ALPHA_PW  = "-c";
    public static final String FLAG_NUM_ONLY_PW  = "-n";
    public static final String  FLAG_PRINT_LAST  = "-p";
    public static final String   FLAG_PRINT_ALL  = "-a";
    public static final String  FLAG_PRINT_FILE  = "-f";
    public static final String  FLAG_DELETE_ALL  = "-D";
    public static final String     FLAG_PW_SIZE  = "-s";
    public static final String        FLAG_HELP  = "-h";
    public static final    int  DEFAULT_PW_SIZE  = 8;
    public static final String PW_DATABASE_FILE  = ".pwcollection.txt";
    public static final String    PW_PRINT_FILE  = "SavedPasswords.txt";
    private static java.nio.file.Path   databasePath;

    public static String printDirections()
    {
        return "Eric McDaniel's Random Password Generator\n\n"
            + "Purpose  ---\tThis program generates a random password\n"
            + "\t\tbased on the command line flag provided.\n"
            + "\t\tThe passwords can either be alpha-numeric or\n"
            + "\t\tnumeric only, are customizable in length, and\n"
            + "\t\twill be stored into memory for future retrieval.\n\n\n"
            + "Usage:\n"
            + "  " + FLAG_ALPHA_PW    + "\t\tRandom password containing a mixture of\n (or..)"
            +                           "\t\tcharacters and numbers.\n"
            + "  " + FLAG_NUM_ONLY_PW + "\t\tRandom password containing numbers only.\n\n"
            + "  " + FLAG_PRINT_LAST  + "\t\tPrint the most recent password.\n"
            + "  " + FLAG_PRINT_ALL   + "\t\tPrint ALL of the timestamped passwords.\n"
            + "  " + FLAG_PRINT_FILE  + "\t\tRedirect ALL timestamped passwords to\n"
            +                           "\t\ta textfile named \"SavedPasswords.txt\"\n\n\n"
            + "Optional:\n"
            + "  " + FLAG_PW_SIZE + " <VALUE>\tCustomize the password's length. The default\n"
            +                          "\t\tsize of " + DEFAULT_PW_SIZE
            +                          " numbers and/or characters will\n"
            +                          "\t\totherwise be used.\n"
            + "  " + FLAG_DELETE_ALL + "\t\tClear entire database.\n"
            + "  " + FLAG_HELP + "\t\tPrint this help dialog.\n";
    } // End String printDirections()

    public static void main(String[] args)
    {
        // Establish an os path for the file. File not created yet. 
        java.nio.file.Path databasePath = java.nio.file.Paths.get(System.getProperty("user.home")
                                        + java.io.File.separator + PW_DATABASE_FILE);
        
        // Reject execution if no arguments were provided
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
            // Create password generating object. Constructor will parse args
            PasswordGen pwObj = new PasswordGen(args, databasePath);
            if (pwObj.argsArrayList.contains(FLAG_PRINT_FILE))
            {
                printFile = new PrintWriter(new FileWriter(PW_PRINT_FILE));
                printFile.print(pwObj.printPasswords());
                System.out.println("Passwords have successfully been printed on to:\n"
                                + "\t\"SavedPasswords.txt\"");
                printFile.close();
                System.exit(0);
            }
            else if (pwObj.argsArrayList.contains(FLAG_PRINT_ALL) ||
                     pwObj.argsArrayList.contains(FLAG_PRINT_LAST))
            {
                System.out.print(pwObj.printPasswords());
                System.exit(0);
            }
            else if (pwObj.argsArrayList.contains(FLAG_ALPHA_PW) ||
                     pwObj.argsArrayList.contains(FLAG_NUM_ONLY_PW) ||
                     pwObj.argsArrayList.contains(FLAG_PW_SIZE))
            {
                // Instantiate io objects
                outWriter = new PrintWriter(new FileWriter(databasePath.toString(), true));
                String completedPassword = pwObj.generatePW();

                // Use Date and DateFormat objects to make formatted timestamps
                java.util.Date date = new java.util.Date();
                String strDateFormatToScreen = "MM/dd/yy, hh:mm:ss a";
                java.text.DateFormat dateFormatToScreen = new java.text.SimpleDateFormat(strDateFormatToScreen);
                String formattedDateToScreen = dateFormatToScreen.format(date);
                String strDateFormatToFile = "MM dd yy hh mm ss a";
                java.text.DateFormat dateFormatToFile = new java.text.SimpleDateFormat(strDateFormatToFile);
                String formattedDateToFile = dateFormatToFile.format(date);

                // Print to the screen with formatted time, append to the database file
                System.out.println("[New Password - generated on " + formattedDateToScreen + "]");
                System.out.println("\t" + completedPassword);
                outWriter.println(formattedDateToFile + " " + completedPassword);
            }
            else
            {
                System.err.println("Unrecognized flag provided.\n"
                + "Revise your entry, or provide -h as an argument at execution.");
                System.exit(1);
            }
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
