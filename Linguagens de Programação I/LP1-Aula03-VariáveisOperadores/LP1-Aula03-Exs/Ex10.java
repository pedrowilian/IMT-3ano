import java.util.Scanner;

public class Ex10 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero para calcular o logaritmo: ");
        double num = sc.nextDouble();
        System.out.print("Digite a base do logaritmo: ");
        double base = sc.nextDouble();
        double log = Math.log(num) / Math.log(base);
        System.out.printf("Logaritmo de base %.2f do numero: %.2f%n", base,log);
        sc.close();
    }
}
