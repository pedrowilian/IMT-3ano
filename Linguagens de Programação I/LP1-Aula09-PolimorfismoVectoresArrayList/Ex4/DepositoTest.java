package Ex4;


import java.util.Scanner;

class DepositoTest {
    public static void main(String[] args) {
        Deposito deposit = new Deposito();
        Scanner scanner = new Scanner(System.in);
        int op;
        int index;

        do {
            System.out.println("1 - Adicionar caixa");
            System.out.println("2 - Remover caixa");
            System.out.println("3 - Procurar caixa");
            System.out.println("4 - Mudar caixa");
            System.out.println("5 - Listar caixas mais pesadas que 10.0");
            System.out.println("6 - Sair");

            System.out.print("Digite a opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> deposit.addBox();
                case 2 -> deposit.removeBox();
                case 3 -> {
                    index = deposit.searchBox();
                    if (index == -1) {
                        System.out.println("Caixa não encontrada");
                    } else {
                        System.out.println(deposit.boxes.get(index).getData());
                    }
                }
                case 4 -> deposit.changeBox();
                case 5 -> deposit.returnByBoxByWeight(10.0);
                case 6 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }


        }while(op != 6);
        scanner.close();
    }

}
