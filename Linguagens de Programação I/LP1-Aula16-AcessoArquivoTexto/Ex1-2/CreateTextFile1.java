// Writing data to a text file with class Formatter.
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile1 {
    private Formatter output; // object used to output text to file

    public void openFile() // enable user to open file
    {
        try {
            output = new Formatter("clients.txt");
        } // end try
        catch (SecurityException securityException) {
            System.err.println("You do not have write access to this file.");
            System.exit(1);
        } // end catch
        catch (FileNotFoundException filesNotFoundException) {
            System.err.println("Error creating file.");
            System.exit(1);
        } // end catch
    } // end method openFile

    public void addUsers() // add records to file
    {
        // object to be written to file
        User user = new User();
        Scanner input = new Scanner(System.in);
        System.out.printf("%s\n%s\n\n",
                "To terminate input, type the end-of-file indicator",
                "when you are prompted to enter input.",
                "On UMIX/Linux/Mac OS X type <ctrl> d then press Enter",
                "On Windows type <ctrl> z then press Enter or <ctrl> e");
        System.out.printf("%s\n%s",
                "Enter Name <SPACE> Password <ENTER>",
                "? ");
        while (input.hasNext()) // loop until end-of-file indicator
        {
            try // output values to file
            { // retrieve data to be output
                user.setName(input.next()); // Name
                user.setPassword(input.next()); // Password
                if (user.getName() != null && user.getPassword() != null) { // write new User
                    output.format("%s %s\n", user.getName(), user.getPassword());
                } // end if
                else {
                    System.out.println(
                            "Account number must be greater than 0.");
                } // end else
            } // end try
            catch (FormatterClosedException formatterClosedException) {
                System.err.println("Error writing to file.");
                return;
            } // end catch
            catch (NoSuchElementException elementException) {
                System.err.println("Invalid input. Please try again.");
                input.nextLine(); // discard input so user can try again
            } // end catch
            System.out.printf("%s\n ", "Enter Name <SPACE> Password <ENTER>", "? ");
        } // end while
        input.close();
    } // end method addRecords

    public void closeFile() // close file
    {
        if (output != null)
            output.close();
    } // end method closeFile
} // end class CreateTextFile