import java.util.Scanner;

public class Ex9 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num = sc.nextDouble();
        num = Math.log10(num);
        System.out.printf("Logaritmo de base 10 do numero: %.2f%n", num);
        sc.close();
    }
}
