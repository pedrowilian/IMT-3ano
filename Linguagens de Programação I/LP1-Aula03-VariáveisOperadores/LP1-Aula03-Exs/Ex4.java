import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite a string 1: ");
    String string1 = sc.next();
    System.out.print("Digite a string 2: ");
    String string2 = sc.next();
    System.out.print("Digite a string 3: ");
    String string3 = sc.next();
    System.out.println(string1.length()+string2.length()+string3.length());
    sc.close();
    }
}
