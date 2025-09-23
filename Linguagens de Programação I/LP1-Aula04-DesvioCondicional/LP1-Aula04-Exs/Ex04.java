import java.util.Scanner;

public class Ex04 {
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
                System.out.println("Ordem dos numeros em ordem crescente: "+num3+" "+num2+" "+num1);
            }
            else{
                System.out.println("Ordem dos numeros em ordem crescente: "+num2+" "+num3+" "+num1);
            }
        }
        else if(num2>num1 && num2>num3){
            if(num1>num3){
                System.out.println("Ordem dos numeros em ordem crescente: "+num3+" "+num1+" "+num2);
            }
            else{
                System.out.println("Ordem dos numeros em ordem crescente: "+num1+" "+num3+" "+num2);
            }
        }
        else if(num3>num1 && num3>num2){
            if(num1>num2){
                System.out.println("Ordem dos numeros em ordem crescente: "+num2+" "+num1+" "+num3);
            }
            else{
                System.out.println("Ordem dos numeros em ordem crescente: "+num1+" "+num2+" "+num3);
            }
        }
        sc.close();
        }
    }
