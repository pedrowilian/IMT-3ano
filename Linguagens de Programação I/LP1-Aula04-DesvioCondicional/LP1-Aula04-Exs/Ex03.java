import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        double num1 = sc.nextDouble();
        if (num1>0){
            double resposta = Math.sqrt(num1);
            System.out.println("raiz quadrada do numero positivo: "+resposta);
        }
        else{
            double resposta = Math.pow(num1,2);
            System.out.println("Quadrado do numero negativo: "+resposta);
        }
        sc.close();
    }
}
