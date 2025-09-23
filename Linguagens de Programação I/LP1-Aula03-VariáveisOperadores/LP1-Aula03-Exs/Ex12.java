import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite uma data formato ddmmaa: ");
    int data = sc.nextInt();
    int dia = data/10000;
    int mes = (data%10000)/100;
    int ano = data%100;
    System.out.println("dia: "+dia);
    System.out.println("mes: "+mes);
    System.out.println("ano: "+ano);
    sc.close();
    }
}
