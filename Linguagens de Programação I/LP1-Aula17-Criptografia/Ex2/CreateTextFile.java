// Writing data to a text file with class Formatter.
import java.io.FileNotFoundException;
import java.util.Formatter;

public class CreateTextFile {
    private Formatter output; // object used to output text to file
    public void openFile(String Filename) // enable user to open file
    {
        try {
            output = new Formatter(Filename);
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

    public void addText(String data) // add Text to file
    {
        output.format("%s\n", data);
    } // end method addText

    public void closeFile() // close file
    {
        if (output != null)
            output.close();
    } // end method closeFile
} // end class CreateTextFile