import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        if (20<num1 && num1<90){
            System.out.println("Numero digitado esta entre 20 e 90 (intervalo aberto):");
        }
        else{
            System.out.println("Numero digitado nao esta entre 20 e 90 (intervalo aberto):");
        }
        sc.close();
    }
}
