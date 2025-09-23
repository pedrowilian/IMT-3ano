import java.util.Scanner;

public class Ex13 {
  public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int soma = 0;
        System.out.print("Digite um número positivo (para sair digite um número não positivo): ");
        int n = sc.nextInt();
        while (n > 0) {
            if (Primo(n))
                soma+=1;
            System.out.print("Digite um número positivo (para sair digite um número não positivo): ");
            n = sc.nextInt(); 
        }
        System.out.println("No total tem: "+soma+" numeros primos");
        sc.close(); 
    }
    public static boolean Primo(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
