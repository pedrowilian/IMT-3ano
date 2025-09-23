// Pedro Wilian Palumbo Bevilacqua RA: 23.01307-9

import java.util.Scanner;

public class Ex01 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.print("Digite o segundo numero: ");
        int num2 = sc.nextInt();
        int resposta = num1 + num2;
        if (resposta>10){
            System.out.println("A soma maior que 10 da: "+ resposta);
        }
        else{
            System.out.println("Soma dos numeros menor que 10");
        }
        sc.close();
    }
}   
