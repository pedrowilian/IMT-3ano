import java.util.Scanner;

public class Ex18 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();

        if (num1>num2)
            System.out.println("Ordem dos numeros em ordem crescente: "+num2+" "+num1);
        else
            System.out.println("Ordem dos numeros em ordem crescente: "+num1+" "+num2);
        sc.close();
        }
}
