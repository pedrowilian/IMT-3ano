import java.util.Scanner;

public class Ex17 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Tabela farenheit para graus celsius (digite em farenheit)");
        System.out.print("Digite o limite inferior: ");
        double inf = sc.nextDouble();
        System.out.print("Digite o limite superior: ");
        double sup = sc.nextDouble();
        System.out.print("Digite o incremento: ");
        double inc = sc.nextDouble();
        double c = 0;
        for (double i = inf; i<=sup; i+=inc){
            c = 5*(i-32)/9;
            System.out.format("Farenheit: "+i+" em graus celsius: %.1f \n",c);
        }
    }
}
