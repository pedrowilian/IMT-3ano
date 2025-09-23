import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile
{
    private Scanner input;

    public void openFile(String path){
        try{
            input = new Scanner( new File( path ) );
        } 
        catch ( FileNotFoundException fileNotFoundException ){
            System.err.println( "Error opening file." );
            System.exit( 1 );
        } 
    } 

    public String readFile() {
        String linha;
        String txt = "";
        try{
            while ( input.hasNext() ){

                linha = input.nextLine();
                txt += linha;
            } 
        } 
        catch( NoSuchElementException elementException ){
            System.err.println( "File improperly formed." );
            input.close();
            System.exit( 1 );
        } 
        catch( IllegalStateException stateException ){
            System.err.println( "Error reading from file." );
            System.exit( 1 );
        } 
        return txt;
    } 

    public void closeFile(){
        if ( input != null )
            input.close();
    } 
}
