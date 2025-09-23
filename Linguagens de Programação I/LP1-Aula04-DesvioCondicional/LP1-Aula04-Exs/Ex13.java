import java.util.Scanner;

public class Ex13 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero de 3 algarismos: ");
        int num1 = sc.nextInt();
        int resposta = num1%100/10;
        if (resposta%2==0)
            System.out.println("A dezena do numero é par");
        else
            System.out.println("A dezena do numero é impar");
        sc.close();
    }
}
