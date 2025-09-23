import java.util.Scanner;

public class Ex7 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite um numero de 5 digitos: ");
    int num = sc.nextInt();
    num = num%100;
    num = num/10;
    System.out.println("A dezena do numero digitado: "+num);
    sc.close();
    }
}
