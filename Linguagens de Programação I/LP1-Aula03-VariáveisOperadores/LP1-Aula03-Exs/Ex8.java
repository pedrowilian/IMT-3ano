import java.util.Scanner;

public class Ex8 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um ângulo em graus: ");
        double ang = sc.nextDouble();
        double rad = Math.toRadians(ang);
        System.out.printf("Seno: %.2f%n", Math.sin(rad));
        System.out.printf("Cosseno: %.2f%n", Math.cos(rad));
        System.out.printf("Tangente: %.2f%n", Math.tan(rad));
        System.out.printf("Secante: %.2f%n", 1 / Math.cos(rad));
        System.out.printf("Cossecante: %.2f%n", 1 / Math.sin(rad));
        System.out.printf("Cotangente: %.2f%n", 1 / Math.tan(rad));
        sc.close();
    }
}
