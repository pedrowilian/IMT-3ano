import java.util.Scanner;

public class Ex06 {
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        if (num1>20)
            System.out.println("Numero digitado maior que 20");
        else if (num1==20)
            System.out.println("Numero digitado é igual a 20");
        else
            System.out.println("Numero digitado menor que 20");
        sc.close();
    } 
}
