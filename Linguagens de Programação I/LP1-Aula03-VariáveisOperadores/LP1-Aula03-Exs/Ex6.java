import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double juros = 10;
        int meses = 8;
        double Valorinicial = 100;
        double Valorfinal = Valorinicial * Math.pow((1+juros/100),meses);
       
        System.out.printf("%.2f%n", Valorfinal);
        sc.close();
    }
}
