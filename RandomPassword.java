public class RandomPassword
{
    public static int getRandom()
    {
        boolean notValid = true;
        int number = -1;
        while (notValid)
        {
            number = (new java.util.Random().nextInt(74) + 48);
            if (!(number >= 58 && number <=65) &&!(number >= 91 && number <= 96))
                notValid = false;
        }
        return number;
    } // End int getRandom()

    public static void main(String[] args)
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
                char letter = 'A';
                letter -= 65;
                letter += getRandom();
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
    } // End main()
} // End class RandomPassword