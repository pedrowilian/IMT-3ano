import java.util.Scanner;

public class Ex23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total = 0;

        while (true) {
            double preco;
            int quantidade;
            do {
                System.out.print("Digite o preço da mercadoria (valor positivo): ");
                preco = sc.nextDouble();
                if (preco <= 0)
                    System.out.println("Preço inválido! Digite um valor positivo.");
            } while (preco <= 0);

            do {
                System.out.print("Digite a quantidade comprada (digite zero para encerrar a compra): ");
                quantidade = sc.nextInt();
                if (quantidade < 0)
                    System.out.println("Quantidade inválida! Digite um valor positivo.");
            } while (quantidade < 0);

            if (quantidade == 0)
                break;

            total += preco * quantidade;
        }

        System.out.printf("Total a ser pago: R$ %.2f%n", total);
        sc.close();
    }
}
