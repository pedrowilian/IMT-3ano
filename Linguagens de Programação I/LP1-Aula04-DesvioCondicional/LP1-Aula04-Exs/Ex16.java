import java.util.Scanner;

public class Ex16 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o primeiro numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o segundo numero: ");
        double num2 = sc.nextDouble();
        if (num1==num2)
            System.out.println("Os numeros sao iguais");
        else
            System.out.println("Os numeros sao diferentes");
        sc.close();
        }
}
