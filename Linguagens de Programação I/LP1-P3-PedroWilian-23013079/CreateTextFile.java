import java.io.FileNotFoundException;
import java.util.Formatter;

public class CreateTextFile {
    private Formatter output; 
    public void openFile(String Filename) {
        try {
            output = new Formatter(Filename);
        } 
        catch (SecurityException securityException) {
            System.err.println("You do not have write access to this file.");
            System.exit(1);
        } 
        catch (FileNotFoundException filesNotFoundException) {
            System.err.println("Error creating file.");
            System.exit(1);
        } 
    } 

    public void addText(String data) {
        output.format("%s", data);
    } 

    public void closeFile() {
        if (output != null)
            output.close();
    } 
} 