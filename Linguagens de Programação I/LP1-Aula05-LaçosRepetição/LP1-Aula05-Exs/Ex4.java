import java.util.Scanner;

public class Ex4 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um nome (digite FIM para sair): ");
        String nome = sc.nextLine();
        while (!"FIM".equals(nome)) {
            System.out.println(nome);
            System.out.print("Digite um nome (digite FIM para sair): ");
            nome = sc.nextLine(); 
        }

        sc.close(); 
    }
}   