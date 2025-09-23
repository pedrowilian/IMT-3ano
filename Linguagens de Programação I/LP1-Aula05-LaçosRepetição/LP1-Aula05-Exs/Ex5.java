import java.util.Scanner;

public class Ex5 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número (digite -999 para sair): ");
        double n = sc.nextDouble();
        while (n !=-999) {
            System.out.println("raiz quadrada: "+Math.sqrt(n));
            System.out.println("Seu inverso: "+ 1/n);
            System.out.print("Digite um número (digite -999 para sair): ");
            n = sc.nextDouble();
        }
        System.out.print("Saindo...");

        sc.close(); 
    }
}
