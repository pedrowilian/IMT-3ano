// This program reads a text file and displays each record.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile
{
    private Scanner input;

    // enable user to open file
    public void openFile()
    {
        try
        {
            input = new Scanner( new File( "teste.txt" ) );
        } // end try
        catch ( FileNotFoundException fileNotFoundException )
        {
            System.err.println( "Error opening file." );
            System.exit( 1 );
        } // end catch
    } // end method openFile

    public String readFile() // read record from file
    {
        // Variable to hold text in file
        String linha;
        String txt = "";
        try // read records from file using Scanner object
        {
            while ( input.hasNext() )
            {

                linha = input.nextLine();
                txt += linha + "\n";
            } // end while
        } // end try
        catch( NoSuchElementException elementException )
        {
            System.err.println( "File improperly formed." );
            input.close();
            System.exit( 1 );
        } // end catch
        catch( IllegalStateException stateException )
        {
            System.err.println( "Error reading from file." );
            System.exit( 1 );
        } // end catch
        return txt;

    } // end method readRecords

    public void closeFile() // close file and terminate application
    {
        if ( input != null )
            input.close(); // close file
    } // end method closeFile
} // end class ReadTextFile
