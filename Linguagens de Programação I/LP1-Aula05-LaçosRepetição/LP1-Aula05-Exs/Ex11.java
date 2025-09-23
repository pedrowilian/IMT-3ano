import java.util.Scanner;

public class Ex11 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int contador21 = 0;
        int contador50 = 0;
        System.out.print("Digite uma idade (para sair digite uma idade fora de 0-120 anos): ");
        int idade = sc.nextInt();
        while (idade >= 0 && idade <=120) {
            if (idade<21)
                contador21++;
            else if (idade>50)
                contador50++;
            System.out.print("Digite uma idade (para sair digite uma idade fora de 0-120 anos): ");
            idade = sc.nextInt();
        }
        System.out.println("Total de pessoas com menos de 21 ano: "+contador21);
        System.out.println("Total de pessoas com menos de 50 ano: "+contador50);
        sc.close(); 
    }
}
