public class RandomPassword
{
    public static int getRandomChar()
    {
        boolean notValid = true;
        int number = -1;
        while (notValid)
        {
            number = (new java.util.Random().nextInt(74) + 48);
            if (!(number >= 58 && number <= 65) && !(number >= 91 && number <= 96))
                notValid = false;
        }
        return number;
    } // End int getRandomChar()

    public static void main(String[] args)
    {
        if (args.length < 1)
            System.out.println("Improper command line argument provided.\n\nTo Run:"
            + "\t\tThis program will generate a random password\n"
            + "\t\tbased on the command line flag provided.\n\n"
            + "Required:\n"
            + "-c\t\tRandom password containing a mixture of\n\t\tcharacters and numbers\n"
            + "-n\t\tRandom password containing numbers only\n\n"
            + "Optional:\n"
            + "-s <VALUE>\tThe size of the password's length. This\n\t\twill be promped if not provided\n");
        else
        {
            System.out.print("Password character length: ");
            java.io.PrintWriter out = null;
            try
            {
                // Instantiate io objects
                int length = new java.util.Scanner(System.in).nextInt();            
                out = new java.io.PrintWriter(new java.io.FileWriter("passwords.txt", true));
                String completedPassword = "";

                // Loop generating random letter, concatenate with complete string
                for (int i = 0; i < length; ++i)
                {
                    char letter = 'A' - 65;
                    letter += getRandomChar();
                    completedPassword += letter;
                }

                // Print to the screen and append to the file with formatted time
                System.out.println(completedPassword);
                java.util.Date date = new java.util.Date();
                String strDateFormat = "MM/dd/yy hh:mm:ss a";
                java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(strDateFormat);
                String formattedDate= dateFormat.format(date);
                out.println("[Password from " + formattedDate + "]");
                out.println("\t" + completedPassword);
            }
            catch (java.util.InputMismatchException ex)
            {
                System.out.println("Cannot convert to integer. Restart");
            } 
            catch (java.io.IOException ex)
            {
                System.out.println("Error with file. Program Dumped");
            }
            finally
            {
                if (out != null)
                    out.close();
            }
        } // End if-args.length
    } // End main()
} // End class RandomPassword