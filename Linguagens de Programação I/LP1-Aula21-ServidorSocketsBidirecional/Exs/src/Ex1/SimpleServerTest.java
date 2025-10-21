package Ex1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class SimpleServerTest {
    // Especifica endereco do IP local para o servidor
    public static final String ENDERECO = "127.0.0.1";
    // Especifica porta livre do computador para o servidor escutar
    public static final int PORTA = 3334;

    private static ServerSocket servidor;
    private static Socket clienteAceito;

    private static Scanner entrada;
    private static PrintStream saida;

    public static void main(String args[]) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            // Inicia servidor e cria servico de escuta por porta via socket
            iniciaServidor();
            // Cria canal de comunicacao para conexao do cliente (servico)
            aguardaConexaoCliente();
            // Escutando e respondendo as mensagens do cliente
            conversaComCliente();
            // Fecha canal de comunicacao da conexao do cliente (servico)
            encerraConexaoCliente();
            // Fecha servico de escuta por porta via socket e encerra servidor
            encerraServidor();
        } catch (IOException ex) {
            System.out.println("Erro no Servidor: " + ex.getMessage());
        }
    }

    private static void iniciaServidor() throws IOException {
        // Cria servico de escuta pelo servidor na porta especificada via socket
        servidor = new ServerSocket(PORTA);
        System.out.println("Servidor iniciado e escutando a porta " + PORTA);
    }

    private static void aguardaConexaoCliente() throws IOException {
        // Cria canal de comunicacao para conexao do cliente (servico)
        clienteAceito = servidor.accept();
        // Apresenta endereco do cliente conectado ao servidor
        System.out.println("Cliente IP " +
                clienteAceito.getInetAddress().getHostAddress() +
                " conectado ao Servidor pela porta " + PORTA);
        // Obtem a entrada do canal (socket)
        entrada = new Scanner(clienteAceito.getInputStream());
        // Objeto para enviar mensagem ao cliente
        saida = new PrintStream(clienteAceito.getOutputStream());
    }

    private static void conversaComCliente() throws IOException {
        String msg;
        // Escutando as mensagens do cliente que chegam ao servidor
        while (entrada.hasNextLine()) { // Aguarda proxima mensagem do cliente...
            // Le mensagem do Cliente
            msg = leMensagemCliente();
            retornaMensagemCliente(msg); // Servidor retorna mensagem ao cliente
        }
    }

    private static String leMensagemCliente() throws IOException {
        String msg = entrada.nextLine(); // Le mensagem do Cliente
        System.out.print("Chegou do Cliente: ");
        System.out.println(msg); // Imprime na tela a mensagem de entrada
        return msg;
    }

    private static void retornaMensagemCliente(String msg) throws IOException {
        // Servidor retorna mensagem ao Cliente
        saida.println(msg);
        System.out.print("Ecoou ao Cliente: ");
        System.out.println(msg); // Imprime na tela a mensagem de saida
    }

    private static void encerraConexaoCliente() throws IOException {
        // Fechando o canal de entrada do servidor e o servico de conexao ao servidor
        entrada.close(); // Fecha o canal de comunicacao
        System.out.println("Cliente se desconectou do Servidor!");
    }

    private static void encerraServidor() throws IOException {
        // Fecha servico de escuta por porta via socket e encerra servidor
        servidor.close(); // Fecha o servico
        System.out.println("Servidor finalizado!");
    }
}
