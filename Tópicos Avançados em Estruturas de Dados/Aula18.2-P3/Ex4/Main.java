import java.util.Scanner;

public class Main {
    private static final String[] PRIORITY_DESCRIPTIONS = {
        "1. Prioridade Máxima (Idosos com 80 anos ou mais)",
        "2. Prioridade Alta (Idosos com 60 anos ou mais)",
        "3. Prioridade Média (Grávidas, Puérperas e Deficientes)",
        "4. Prioridade Baixa (Obesos e Pessoas acompanhadas de crianças)",
        "5. Prioridade Mínima (Sem restrições)"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientHeap heap = new PatientHeap(100);
        System.out.println("Sistema ACME");
        displayMenu();

        while (true) {
            System.out.print("Digite a opção (1-5 para prioridade, ? para chamar próximo, ou 's' para sair): ");
            String input = scanner.nextLine().trim();

            if (input.equals("?")) {
                Patient nextPatient = heap.remove();
                if (nextPatient != null) {
                    System.out.println(nextPatient);
                } else {
                    System.out.println("Fila Vazia");
                }
            } else if (input.equals("s")) {
                System.out.println("Sistema encerrado.");
                break;
            } else {
                try {
                    int priority = Integer.parseInt(input);
                    if (priority >= 1 && priority <= 5) {
                        Patient patient = new Patient(priority);
                        heap.insert(patient);
                        System.out.println("Senha " + patient.getTicketNumber());
                    } else {
                        System.out.println("Opção inválida. Escolha entre 1 e 5.");
                        displayMenu();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Use 1-5, ?, ou 's'.");
                    displayMenu();
                }
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMenu de Prioridades:");
        for (String description : PRIORITY_DESCRIPTIONS) {
            System.out.println(description);
        }
    }
}