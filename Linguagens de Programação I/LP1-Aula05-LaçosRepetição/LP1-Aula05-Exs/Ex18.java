import java.util.Scanner;

public class Ex18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número positivo: ");
        int n = sc.nextInt();
        int resultado = fatorial(n);
        System.out.println(resultado);
        sc.close();
    }
    public static int fatorial(int n) {
        int total = 1;
        for (int i = 1; i <= n; i++)
            total *= i;
        return total;
    }
}
