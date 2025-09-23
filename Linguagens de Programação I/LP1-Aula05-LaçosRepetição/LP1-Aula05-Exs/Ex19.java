import java.util.Scanner;

public class Ex19 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o limite superior: ");
        double sup = sc.nextDouble();
        System.out.print("Digite o incremento: ");
        double inc = sc.nextDouble();
        double c = 0;
        System.out.print("A sequencia é:");
        for (double i = 0; i<=sup; i+=inc){
            System.out.print(" "+i);
        }
    }
}
