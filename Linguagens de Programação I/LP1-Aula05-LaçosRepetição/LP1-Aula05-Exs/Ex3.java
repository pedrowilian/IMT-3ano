import java.util.Scanner;

public class Ex3 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        System.out.print("Digite um número (para sair digite 0): ");
        int n = sc.nextInt();
        while (n != 0) {
            if (n>=100 && n<=200)
                contador++;
            System.out.print("Digite um número (para sair digite 0): ");
            n = sc.nextInt(); 
        }
            System.out.println("A quantidade de numeros digitados entre 100 e 200 foram: " + contador);

        sc.close(); 
    }
}
