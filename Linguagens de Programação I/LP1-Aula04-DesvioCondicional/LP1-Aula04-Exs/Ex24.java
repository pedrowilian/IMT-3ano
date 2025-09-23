import java.util.Scanner;

public class Ex24 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o 1 numero: ");
        double num1 = sc.nextDouble();
        System.out.print("Digite o 2 numero: ");
        double num2 = sc.nextDouble();
        System.out.print("Digite o 3 numero: ");
        double num3 = sc.nextDouble();
        System.out.print("Digite o 4 numero: ");
        double num4 = sc.nextDouble();
        System.out.print("Digite o 5 numero: ");
        double num5 = sc.nextDouble();
        double[] vetor = new double[5];
        vetor[0] = num1;
        vetor[1] = num2;
        vetor[2] = num3;
        vetor[3] = num4;
        vetor[4] = num5;
        double maior = num1;
        double menor = num1;
        for (int i = 1; i < 5; i++) {
            if (vetor[i] > maior)
                maior = vetor[i]; 
        }
        System.out.println("O maior valor do vetor é: " + maior); 
        for (int i = 1; i < 5; i++) {
            if (vetor[i] < menor)
                menor = vetor[i]; 
        }
        System.out.println("o menor valor do vetor é: "+ menor);
        sc.close();
    }

}