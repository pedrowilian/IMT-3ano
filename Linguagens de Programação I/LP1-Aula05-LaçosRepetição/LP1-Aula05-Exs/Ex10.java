import java.util.Scanner;

public class Ex10 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        double consumot1 = 0,consumot2 =0,consumot3=0;
        System.out.print("Codigo do consumidor (digite 0 para sair): ");
        int codigo = sc.nextInt();
        System.out.print("Digite o consumo mensal em Kwh: ");
        double consumo = sc.nextDouble();
        System.out.println("Digite o tipo de consumidor:\n1 - Residencial\n2 - comercial\n3 - industrial");
        int tipo = sc.nextInt();
        
        while (codigo !=0) {
            if (tipo==1)
                consumot1 += 0.3*consumo;
            else if (tipo==2)
                consumot2 += 0.5*consumo;
            else if (tipo==3)
            consumot3 +=0.7*consumo;
            System.out.print("Codigo do consumidor (digite 0 para sair): ");
            codigo = sc.nextInt();
            if(codigo==0)
                break;
            System.out.print("Digite o consumo mensal em Kwh: ");
            consumo = sc.nextDouble();
            System.out.println("Digite o tipo de consumidor:\n1 - Residencial\n 2 - comercial\n 3 - industrial");
            tipo = sc.nextInt();
            }
        
        System.out.println("Total de consumo para os 3 tipos:");
        System.out.println("Tipo 1 - "+consumot1+"\nTipo 2 - "+consumot2+"\nTipo 3 - "+consumot3+"\nMedia de consumo 1 e 2 - "+(consumot1+consumot2)/2);
        sc.close(); 
    }
}
