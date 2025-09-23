import java.util.Scanner;

public class Ex13 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite uma data formato dd/mm/aa: ");
    String data = sc.next();
    String dia = data.substring(0,2);
    String mes = data.substring(3,5);
    String ano = data.substring(6);
    System.out.println("dia: "+dia);
    System.out.println("mes: "+mes);
    System.out.println("ano: "+ano);
    sc.close();
    }
}
