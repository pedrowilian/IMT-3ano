import java.util.Scanner;

public class Ex20 {
     public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o primeiro numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o segundo numero: ");
        double num2 = sc.nextDouble();
        if (num1<num2){
            double res1 = Math.pow(num1, 2);
            double res2 = Math.sqrt(num2);
            System.out.println("Raiz do menor numero: "+res1);
            System.out.println("Quadrado do maior numero: "+res2);
        }
        else if(num2<num1){
            double res1 = Math.pow(num2, 2);
            double res2 = Math.sqrt(num1);
            System.out.println("Raiz do menor numero: "+res1);
            System.out.println("Quadrado do maior numero: "+res2);
        }
        sc.close();
    } 
}
