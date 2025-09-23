import java.util.Scanner;

public class Ex07 {
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o sexo (f ou m): ");
        String sexo = sc.nextLine().toLowerCase();
        System.out.print("Digite a idade: ");
        int idade = sc.nextInt();

        if (sexo.equals("f")&& idade<25)
            System.out.println(nome +" ACEITA");
        else
        System.out.println(nome +" NAO ACEITA");
    sc.close();
    } 
}
