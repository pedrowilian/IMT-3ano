import java.util.Scanner;

public class Ex9 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número (digite -999 para sair): ");
        double n = sc.nextDouble();
        while (n !=-999) {
            System.out.print("Seus divisores são: ");
            for (int i=1;i<=n;i++){
                if ((n%i)==0){
                    System.out.print(i+" ");
                }
            }
            System.out.print("\nDigite um número (digite -999 para sair): ");
            n = sc.nextDouble();
        }
        System.out.print("Saindo...");
        sc.close(); 
    }
}
