package saver;

import java.io.FileNotFoundException;
import java.util.*;

public class TextFileSaver
{  private Formatter output;

   public void openFilWrite(String filename)
   {  try
      {
         output = new Formatter( filename );
      }  // end try
      catch( SecurityException securityException )
      {  System.err.println( "You do not have write access to this file." );
         System.exit( 1 );
      }  // end catch
      catch( FileNotFoundException filesNotFoundException )
      {  System.err.println( "Error creating file." );
         System.exit( 1 );
      } // end catch
   } // end method openFilWrite

   public void saveText(String text)
   {
      { try
         {

            output.format( "%s", text);
         }  // end try
         catch ( FormatterClosedException formatterClosedException )
         {  System.err.println( "Error writing to file." );
            return;
         } // end catch
         catch ( NoSuchElementException elementException )
         {  System.err.println( "Invalid input. Please try again." );
         }  // end catch

        }
    }

  public void closeFile() // close file
   {  if ( this.output != null )
         this.output.close();
   }
}
