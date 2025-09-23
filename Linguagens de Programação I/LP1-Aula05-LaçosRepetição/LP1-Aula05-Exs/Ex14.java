import java.util.Scanner;

public class Ex14 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número (digite -9999 para sair): ");
        double n = sc.nextDouble();
        double aux = -9999;
        while (n !=-9999) {
            if (n>aux)
                aux = n;
            System.out.print("Digite um número (digite -9999 para sair): ");
            n = sc.nextDouble();
        }
        System.out.print("o maior numero foi: "+aux);
        sc.close(); 
    }
}
