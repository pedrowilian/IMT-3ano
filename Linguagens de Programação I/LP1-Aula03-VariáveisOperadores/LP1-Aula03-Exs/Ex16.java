import java.util.Scanner;

public class Ex16 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite o dividendo: ");
    double dividendo = sc.nextDouble();
    System.out.print("Digite o divisor: ");
    double divisor = sc.nextDouble();
    double quociente = dividendo/divisor;
    double resto = dividendo%divisor;
    System.out.println("Dividendo: "+dividendo);
    System.out.println("Divisor: "+divisor);
    System.out.println("Quociente: "+quociente);
    System.out.println("Resto: "+resto);
    sc.close();
    }
}
