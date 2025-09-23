import java.util.Scanner;

public class Ex20 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        for (int i=1;i<=20;i++){
            System.out.println("Pessoa "+i+":");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Sexo (m ou f): ");
            String sexo = sc.nextLine();
            System.out.print("Idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            if (idade>21 && sexo.equals("m"))
                System.out.println("Pessoa com mais de 21 e do sexo masculino: "+nome);
        }
    }
}
