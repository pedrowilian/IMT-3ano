import java.util.Scanner;

public class Ex14 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero de 4 algarismos: ");
        int num1 = sc.nextInt();
        int resposta = num1/100;
        if (resposta%4==0)
            System.out.println("O numero da milhar + centena é multiplo de 4");
        else
            System.out.println("O numero da milhar + centena não é multiplo de 4");
        sc.close();
    }
}
