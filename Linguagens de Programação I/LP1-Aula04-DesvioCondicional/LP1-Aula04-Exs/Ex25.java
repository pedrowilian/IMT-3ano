import java.util.Scanner;

public class Ex25 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();
        System.out.print("Digite o 3 numero: ");
        double num3 = sc.nextDouble();
        if (((num1+num2)>num3) && ((num2+num3)>num1) && ((num1+num3)>num2))
            System.out.println("Pode ser um triangulo");
        else
            System.out.print("Não pode ser um triangulo");
        sc.close();
    }
}
