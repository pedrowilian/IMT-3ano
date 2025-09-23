package Ex5;

import java.util.Scanner;

public class CadastroApp {
    public static void main(String[] args) {
        BancoDeClientes BancoDeClientes = new BancoDeClientes();
        Scanner scanner = new Scanner(System.in);

        int op;
        int id;

        do{

            System.out.println("1 - Adicionar cliente");
            System.out.println("2 - Remover cliente");
            System.out.println("3 - Procurar cliente");
            System.out.println("4 - Mudar cliente");
            System.out.println("5 - Listar clientes");
            System.out.println("6 - Sair");

            System.out.print("Digite a opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch(op)
            {


                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String fone = scanner.nextLine();
                    System.out.print("Digite o ID: ");
                    id = scanner.nextInt();
                    Cliente cliente = new Cliente(nome, fone, id);
                    BancoDeClientes.addCliente(cliente);
                    break;

                case 2:
                    System.out.print("Digite o ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    BancoDeClientes.removeCliente(id);
                    break;

                case 3:
                    System.out.print("Digite o ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();


                    BancoDeClientes.printCliente(id);

                case 4:
                    System.out.print("Digite o ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    BancoDeClientes.changeCliente(id);
                    break;

                case 5:
                    BancoDeClientes.listClientes();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;

            }



        }while (op != 6);
        scanner.close();
    }
}
