import java.util.Scanner;

public class Ex11 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        double resposta1 = num1%21;
        if (resposta1==0)
            System.out.println("o numero é divisivel por 3 e 7");
        else
            System.out.println("o numero não é divisel por 3 e 7");
        sc.close();
    }
}
