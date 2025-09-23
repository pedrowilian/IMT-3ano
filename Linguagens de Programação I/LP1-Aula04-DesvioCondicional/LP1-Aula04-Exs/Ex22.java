import java.util.Scanner;

public class Ex22 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();
        System.out.print("Digite o 3 numero: ");
        double num3 = sc.nextDouble();
        if (num1>num2 && num1>num3){
            double maior = num1;
        }
        else if(num2>num1 && num2>num3){
            double maior = num2;
        }
        else if(num3>num1 && num3>num2){
            double maior = num3;
        }
        sc.close();
    }
}
