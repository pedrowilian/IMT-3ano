import java.util.Scanner;

public class Ex08 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o primeiro numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o segundo numero: ");
        double num2 = sc.nextDouble();
        if (num1>num2)
            System.out.println("o numero maior é: "+num1);
        else if(num1<num2)
            System.out.println("o numero maior é: "+num2);
        sc.close();
    }
}
