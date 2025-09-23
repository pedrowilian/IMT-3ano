import java.util.Scanner;

public class Ex27 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um verbo no infinitivo: ");
        String verbo = sc.nextLine().toLowerCase();
        String fim = verbo.substring(verbo.length()-2,verbo.length());
        if (fim.equals("ar"))
            System.out.println("Verbo da primeira conjugacao");
        else if (fim.equals("er"))
        System.out.println("Verbo da segunda conjugacao");
        else if (fim.equals("ir"))
        System.out.println("Verbo da terceira conjugacao");
        else
        System.out.println("Verbo no infinitivo invalido");
    sc.close();
    } 
}
