import java.util.Scanner;

public class Ex09 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        double resposta = num1%3;
        if (resposta==0)
            System.out.println("o numero é multiplo de 3");
        else
            System.out.println("o numero não é multiplo de 3");
        sc.close();
    }
}
