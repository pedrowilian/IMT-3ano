import java.util.Scanner;

public class Ex24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C1 = 0;
        int C2 = 0;
        int C3 = 0;
        int C4 = 0;
        int totalVotos = 0;
        System.out.println("Urna Eletrônica - Digite o número do candidato para votar:");
        System.out.println("1 - Candidato A");
        System.out.println("2 - Candidato B");
        System.out.println("3 - Candidato C");
        System.out.println("4 - Candidato D");
        System.out.println("Digite -1 para encerrar a eleição.\n");
        while (true) {
            System.out.print("Digite seu voto: ");
            int voto = sc.nextInt();
            if (voto == -1)
                break;
            switch (voto) {
                case 1:
                    C1++;
                    totalVotos++;
                    break;
                case 2:
                    C2++;
                    totalVotos++;
                    break;
                case 3:
                    C3++;
                    totalVotos++;
                    break;
                case 4:
                    C4++;
                    totalVotos++;
                    break;
                default:
                    System.out.println("Voto inválido! Digite um número entre 1 e 4.");
            }
        }
        sc.close();
        System.out.println("\nResultado da Eleição:");
        if (totalVotos > 0) {
            System.out.printf("Candidato 1: %d votos (%.2f%%)%n", C1, (C1 * 100.0 / totalVotos));
            System.out.printf("Candidato 2: %d votos (%.2f%%)%n", C2, (C2 * 100.0 / totalVotos));
            System.out.printf("Candidato 3: %d votos (%.2f%%)%n", C3, (C3 * 100.0 / totalVotos));
            System.out.printf("Candidato 4: %d votos (%.2f%%)%n", C4, (C4 * 100.0 / totalVotos));
        } else
            System.out.println("Nenhum voto foi registrado.");
        System.out.println("Total de eleitores que participaram: " + totalVotos);
    }
}
