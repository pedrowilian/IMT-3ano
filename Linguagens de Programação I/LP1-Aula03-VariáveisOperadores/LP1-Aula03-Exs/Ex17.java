import java.util.Scanner;

public class Ex17 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite um numero: ");
    double num = sc.nextDouble();
    double pow2 = Math.pow(num,2);
    double sqrt = Math.sqrt(num);
    System.out.println("Numero: "+num);
    System.out.println("Numero ao quadrado: "+pow2);
    System.out.println("Raiz quadrada do numero: "+sqrt);
    sc.close();
    }
}
