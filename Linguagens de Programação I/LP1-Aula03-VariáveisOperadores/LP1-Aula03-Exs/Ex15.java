import java.util.Scanner;

public class Ex15 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite o raio do circulo: ");
    double raio = sc.nextDouble();
    double area = Math.PI*raio*raio;
    System.out.println("Area do circulo: "+area);
    sc.close();
    }
}
