import java.util.Scanner;

public class Ex15 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ano de nascimento: ");
        int anonasc = sc.nextInt();
        System.out.print("Digite o ano atual: ");
        int anoatual = sc.nextInt();
        if ((anoatual-anonasc)<130 && anonasc<=anoatual)
            System.out.println("A idade é: "+ (anoatual-anonasc));
        else
            System.out.println("ERRO digite um ano valido");
        sc.close();
        }
}
