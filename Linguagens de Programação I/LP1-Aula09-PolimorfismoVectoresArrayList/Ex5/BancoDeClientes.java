package Ex5;

import java.util.ArrayList;
import java.util.Scanner;

public class BancoDeClientes {
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public void addCliente(Cliente cliente) {
        if (searchCliente(cliente.getId()) != -1) {
            System.out.println("Cliente já existe");
        } else {
            clientes.add(cliente);
        }
    }

    public void removeCliente(int clienteId) {
        int index = searchCliente(clienteId);
        if (index == -1) {
            System.out.println("Cliente não existe");
        }
        clientes.remove(index);  // Remove o cliente com base no índice
    }

    public void listClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getData());
            }
        }
    }

    public int searchCliente(int clienteId) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteId) {
                return i;  // Retorna o índice do cliente encontrado
            }
        }
        return -1;  // Retorna -1 se o cliente não for encontrado
    }

    public Cliente getCliente(int clienteId) {
        int index = searchCliente(clienteId);
        if (index == -1) {
            return null;  // Retorna null se não encontrar o cliente
        }
        else
            return clientes.get(index);  // Retorna o cliente com o ID encontrado
    }

    public void changeCliente(int clienteId) {
        Cliente cliente = getCliente(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não existe");
        }
        else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Telefone: " + cliente.getFone());
            System.out.println("ID: " + cliente.getId());

            System.out.print("Digite o novo nome: ");
            String nome = scanner.nextLine();
            cliente.setNome(nome);

            System.out.print("Digite o novo telefone: ");
            String fone = scanner.nextLine();
            cliente.setFone(fone);

            System.out.print("Digite o novo ID: ");
            int id = scanner.nextInt();
            cliente.setId(id);
            scanner.nextLine();  // Limpa o buffer
        }
    }

    public void printCliente(int clienteId) {
        Cliente cliente = getCliente(clienteId);
        if (cliente == null)
            System.out.println("Cliente não existe");
        else
            System.out.println(cliente.getData());
    }

    public void printAllClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getData());
            }
        }
    }
}
