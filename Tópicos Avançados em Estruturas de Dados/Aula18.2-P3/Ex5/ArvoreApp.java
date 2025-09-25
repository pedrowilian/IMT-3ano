import java.util.Scanner;

public class ArvoreApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tree tree = new Tree();
        System.out.println("=====================================");
        System.out.println("|     Árvore de Caracteres          |");
        System.out.println("=====================================");
        System.out.println("| Tamanho: 0                        |");
        System.out.println("| Árvore: Árvore Vazia              |");
        System.out.println("=====================================");

        while (true) {
            System.out.print("Digite o caractere do novo nó (ou -1 para sair): ");
            String nodeInput = sc.nextLine().trim();
            if (nodeInput.equals("-1")) {
                System.out.println("=====================================");
                System.out.println("| Saindo da aplicação...            |");
                System.out.println("=====================================");
                break;
            }
            if (nodeInput.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("| Caractere inválido! Tente novamente. |");
                System.out.println("=====================================");
                continue;
            }
            char nodeChar = nodeInput.charAt(0);

            System.out.print("Digite o caractere do nó pai (ou 'null' para raiz): ");
            String parentInput = sc.nextLine().trim();
            Character parentChar = null;
            if (!parentInput.isEmpty() && !parentInput.equalsIgnoreCase("null")) {
                parentChar = parentInput.charAt(0);
            }

            tree.insert(nodeChar, parentChar);
            System.out.println("=====================================");
            System.out.println("| Tamanho: " + String.format("%-26d", tree.size()) + "|");
            System.out.println("| Árvore: " + String.format("%-27s", tree.getPreOrder()) + "|");
            System.out.println("=====================================");
        }
        sc.close();
    }
}