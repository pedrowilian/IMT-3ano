import java.util.Scanner;

public class Ex23 {
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();
        System.out.print("Digite o 3 numero: ");
        double num3 = sc.nextDouble();

        if (num1>num2 && num1>num3){
            if(num2>num3){
                double menor = num3;
                double intermediario = num2;
                double maior = num1;
            }
            else{
                double menor = num2;
                double intermediario = num3;
                double maior = num1;
            }
        }
        else if(num2>num1 && num2>num3){
            if(num1>num3){
                double menor = num3;
                double intermediario = num1;
                double maior = num2;
            }
            else{
                double menor = num1;
                double intermediario = num3;
                double maior = num2;
            }
        }
        else if(num3>num1 && num3>num2){
            if(num1>num2){
                double menor = num2;
                double intermediario = num1;
                double maior = num3;
            }
            else{
                double menor = num1;
                double intermediario = num2;
                double maior = num3;
            }
        }
        sc.close();
        } 
}
