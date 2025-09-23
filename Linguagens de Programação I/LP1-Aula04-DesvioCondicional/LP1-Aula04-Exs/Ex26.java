import java.util.Scanner;

public class Ex26 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();
        System.out.print("Digite o 3 numero: ");
        double num3 = sc.nextDouble();
        if (((num1+num2)>num3) && ((num2+num3)>num1) && ((num1+num3)>num2)){
            if((num1 == num2) && (num2 == num3))
                System.out.println("Este triangulo é equilatero");
            else if (num1 == num2 || num1 == num3 || num2 == num3)
            System.out.println("Este triangulo é isoceles");
            else 
            System.out.println("Este triangulo é escaleno");
        }
        else
            System.out.println("Não é um triangulo");
            
        sc.close();
    }
}
