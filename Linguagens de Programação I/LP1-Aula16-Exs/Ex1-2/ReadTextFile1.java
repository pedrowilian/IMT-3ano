// This program reads a text file and displays each record.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile1
{
    private Scanner input;
    Scanner sc = new Scanner(System.in);
    // enable user to open file
    public void openFile()
    {
        try
        {
            input = new Scanner( new File( "clients.txt" ) );
        } // end try
        catch ( FileNotFoundException fileNotFoundException )
        {
            System.err.println( "Error opening file." );
            System.exit( 1 );
        } // end catch
    } // end method openFile

    public void readUser() // read record from file
    {
        // object to be written to screen
        User user = new User();
        System.out.print("Digite o nome (A ser buscado): ");
        String Name = sc.next();
        System.out.print("Digite a senha (A ser buscada): ");
        String Password = sc.next();
        try // read records from file using Scanner object
        {
            while ( input.hasNext() )
            {
                user.setName( input.next() );  // read name
                user.setPassword( input.next() );   // read Password
                if (user.getName().equals(Name) && user.getPassword().equals(Password)) {
                    System.out.println("Usuário encontrado:");
                    System.out.printf("Nome: %s\nSenha: %s\n", user.getName(), user.getPassword());
                    return; // exit the method after finding the user
                }
            } // end while
            System.out.println("Usuário não encontrado!");
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
    } // end method readRecords

    public void closeFile() // close file and terminate application
    {
        if ( input != null )
            input.close(); // close file
    } // end method closeFile
} // end class ReadTextFile
