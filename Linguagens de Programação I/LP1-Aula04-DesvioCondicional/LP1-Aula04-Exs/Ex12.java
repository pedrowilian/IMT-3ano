import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        if (num1%10 == 0)
            System.out.println("o numero é divisivel por 10, por 5 e por 2");
        else{
        if(num1%5 == 0)
            System.out.println("o numero é divisel por 5");
        else if(num1%2 == 0)
            System.out.println("o numero é divisel por 2");
        else
            System.out.println("o numero não é divisel por 2, por 5 ou por 10");
        }
        sc.close();
    }
}
