import java.util.Scanner;

public class Ex22 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantidade de numero par para digitar: ");
        int quanti = sc.nextInt();
        System.out.print("Numeros pares:");
        for (int i=2;i<=quanti*2;i+=2)
            System.out.print(" "+i);
    }
}
