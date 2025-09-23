import java.util.Scanner;

public class Ex14 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite a variavel a: ");
    double a = sc.nextDouble();
    System.out.print("Digite a variavel b: ");
    double b = sc.nextDouble();
    System.out.print("Digite a variavel c: ");
    double c = sc.nextDouble();
    double x = 2*((a-c)/8)-b*5;
    System.out.println("Resultado da expressao: "+x);
    sc.close();
    }
}
