import java.util.Scanner;

public class Ex21 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Limite superior: ");
        int sup = sc.nextInt();
        System.out.print("Numeros impares inferior ou igual ao limite:");
        for (int i=1;i<sup;i+=2)
            System.out.print(" "+i);
    }
}
