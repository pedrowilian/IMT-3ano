import java.util.Scanner;

public class Ex2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int soma = 0;
        int contador = 0;
        System.out.print("Digite um número positivo para calcular a média (para sair digite um número não positivo): ");
        int n = sc.nextInt();
        while (n > 0) {
            soma += n;
            contador++; 
            System.out.print("Digite um número positivo para calcular a média (para sair digite um número não positivo): ");
            n = sc.nextInt(); 
        }
        if (contador > 0) {
            double media = soma / contador; 
            System.out.println("A média dos números digitados é: " + media);
        } else
            System.out.println("Nenhum número positivo foi digitado.");

        sc.close(); 
    }
}

