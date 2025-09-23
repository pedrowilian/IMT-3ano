import java.util.Scanner;

public class Ex28 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um nome: ");
        String nome = sc.nextLine().toUpperCase();
        char um = nome.charAt(0);
        if(um >= 'A' && um <= 'K')
            System.out.println("Estã no grupo D1");
        else if(um >= 'L' && um <= 'N')
            System.out.println("Estã no grupo D2");
        else 
        System.out.println("Estã no grupo D3");
    sc.close();
    } 
}
