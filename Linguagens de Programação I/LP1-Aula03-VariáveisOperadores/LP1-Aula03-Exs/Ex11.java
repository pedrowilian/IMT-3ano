import java.util.Scanner;

public class Ex11 {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a primeira frase: ");
        String frase1 = sc.nextLine();
        System.out.print("Digite a segunda frase: ");
        String frase2 = sc.nextLine();
        System.out.print("Digite a terceira frase: ");
        String frase3 = sc.nextLine();
        int meio1 = frase1.length() / 2;
        int meio2 = frase2.length() / 2;
        int meio3 = frase3.length() / 2;
        String parte1_1 = frase1.substring(0, meio1);
        String parte1_2 = frase1.substring(meio1);
        String parte2_1 = frase2.substring(0, meio2);
        String parte2_2 = frase2.substring(meio2);
        String parte3_1 = frase3.substring(0, meio3);
        String parte3_2 = frase3.substring(meio3);
        String embaralhado = parte2_1 + parte3_2 + parte2_2 + parte1_1 + parte3_1 + parte1_2;
        String concatenado = frase1 + frase2 + frase3;
        System.out.println("Frases concatenadas: " + concatenado);
        System.out.println("Frase embaralhada: " + embaralhado);
        
        sc.close();
    }
}
