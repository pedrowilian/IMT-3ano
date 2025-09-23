import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o primeiro numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o segundo numero: ");
        double num2 = sc.nextDouble();
        double resposta = num1 + num2;
        if (resposta<=20){
            resposta = resposta - 5;
            System.out.println("Soma menor ou igual a 20 subtraindo 5: "+resposta);
        }
        else{
            System.out.println("Soma dos maior que 20");
        }
        sc.close();
    }
}
