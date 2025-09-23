import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextFileHandler
{  private FileWriter fw;
   private Scanner input;


   public Student readStudentRecord(String ra){
      Student student = null;

      try {
               input = new Scanner(new File("students.txt"));
        while(input.hasNext()){
            String line = input.nextLine();
            String[] studentTxt = line.split(";");
            if(studentTxt[0].equals(ra )){
                    student = new Student(studentTxt[0], studentTxt[1], studentTxt[2], Double.parseDouble(studentTxt[3]), Double.parseDouble(studentTxt[4]), Double.parseDouble(studentTxt[5]), Double.parseDouble(studentTxt[6]));
                return student;
             }
        }
      }
        catch (FileNotFoundException e) {
           System.out.println("File not found");
           return student;
        }

       return student;
   }

   public void addStudentRecord(Student student){
      { try
         {
            File file = new File("students.txt");
             System.out.println(file.getAbsolutePath());
            fw = new FileWriter(file, true);
            fw.write(student.toLine()+"\n");
            fw.close();
         }  // end try
         catch ( FormatterClosedException formatterClosedException )
         {  System.err.println( "Error writing to file." );
         } // end catch
         catch ( NoSuchElementException elementException )
         {  System.err.println( "Invalid input. Please try again." );
         }  // end catch
      catch (IOException e) {
          throw new RuntimeException(e);
      }

      }
    }
}
