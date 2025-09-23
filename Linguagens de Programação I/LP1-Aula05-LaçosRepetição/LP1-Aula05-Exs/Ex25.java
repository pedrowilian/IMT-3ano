import java.util.Scanner;

public class Ex25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        double num1, num2, resultado;
        do {
            System.out.println("\nOperações Disponíveis:");
            System.out.println("1. Adição");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("9. Sair do Programa");
            System.out.print("Digite o número da opção desejada: ");
            opcao = sc.nextInt();
            if (opcao >= 1 && opcao <= 4) {
                System.out.print("Digite o primeiro número: ");
                num1 = sc.nextDouble();
                System.out.print("Digite o segundo número: ");
                num2 = sc.nextDouble();
            } else
                num1 = num2 = 0;
            switch (opcao) {
                case 1:
                    resultado = num1 + num2;
                    System.out.println("Resultado da adição: " + resultado);
                    break;
                case 2:
                    resultado = num1 - num2;
                    System.out.println("Resultado da subtração: " + resultado);
                    break;
                case 3:
                    resultado = num1 * num2;
                    System.out.println("Resultado da multiplicação: " + resultado);
                    break;
                case 4:
                    if (num2 == 0)
                        System.out.println("Erro: divisão por zero não permitida!");
                    else {
                        resultado = num1 / num2;
                        System.out.println("Resultado da divisão: " + resultado);
                    }
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Erro: Digite um numero correto!");
            }
        } while (opcao != 9);
        sc.close();
    }
}
