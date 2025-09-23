package reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtReader {

    public static ArrayList<String> readFile(String path){
        Scanner scanner = openFile(path);
        ArrayList<String> lines = new ArrayList<>();

        try {
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            closeFile(scanner);
            System.exit(1);
        }
        closeFile(scanner);

        return lines;
    }

    private static Scanner openFile(String path){
        Scanner scanner = null;
        try {
            File file = new File(path);
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        return scanner;
    }

    private static void closeFile(Scanner scanner){
        if(scanner != null){
            scanner.close();
        }
    }
}
