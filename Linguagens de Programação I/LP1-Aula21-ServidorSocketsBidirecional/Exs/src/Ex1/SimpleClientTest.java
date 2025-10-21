package Ex1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class SimpleClientTest {
    // Criando a variavel de conexao do tipo Socket
    private static Socket cliente;

    private static Scanner entrada;
    private static PrintStream saida;

    public static void main(String args[]) {
        System.out.println("*v*v*v* CONSOLE DO CLIENTE *v*v*v*");
        try {
            // Cria Cliente e inicia conexao com Servidor via socket
            iniciaCliente();
            // Conversa com o Servidor ate solicitar encerramento de comunicacao
            conversaComServidor();
            // Encerra conexao com a Servidor via socket
            encerraConexaoServidor();
        } catch (IOException ex) {
            System.out.println("Erro no Cliente: " + ex.getMessage());
        }
    }

    private static void iniciaCliente() throws IOException {
        // Cria Cliente com IP e Porta para comunicacao com Servidor via socket
        cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);
        System.out.println("Cliente IP " + SimpleServerTest.ENDERECO +
                " conectado ao Servidor pela porta " + SimpleServerTest.PORTA);
        // Obtem a entrada do canal (socket)
        entrada = new Scanner(cliente.getInputStream());
    }

    private static void conversaComServidor() throws IOException {
        String msg, echo = "";
        // Conversa com o Servidor ate solicitar encerramento de comunicacao
        do {
            System.out.println("Digite na Entrada a mensagem para o Servidor!");
            msg = JOptionPane.showInputDialog("Digite aqui a mensagem para o Servidor (ou <sair> para encerrar)");
            if (!msg.equalsIgnoreCase("sair")) {
                enviaMensagemServidor(msg);
                echo = leMensagemServidor();
                verificaComunicacao(echo, msg);
            }
        } while (!msg.equalsIgnoreCase("sair"));
    }

    private static void enviaMensagemServidor(String msg) throws IOException {
        // Objeto para enviar mensagem ao servidor
        saida = new PrintStream(cliente.getOutputStream());
        // Envia mensagem ao servidor
        saida.println(msg);
        System.out.print("Enviou ao Servidor: ");
        System.out.println(msg);
    }

    private static String leMensagemServidor() throws IOException {
        String msg = entrada.nextLine(); // Le a mensagem recebida do Servidor
        System.out.print("Ecoou do Servidor: ");
        System.out.println(msg); // Imprime na tela a mensagem de entrada
        return msg;
    }

    private static void verificaComunicacao(String echo, String msg) {
        // Verifica se echo coincide com mensagem enviada...
        if (echo.equals(msg)) {
            // echo coincide, portanto, comunicacao OK!
            System.out.println("Comunicacao OK!");
        } else {
            // echo nao coincide, portanto, comunicao NOK!
            System.out.println("Comunicacao com problema!");
        }
    }

    public static void encerraConexaoServidor() {
        // Encerra conexao com o Servidor via socket
        System.out.println("Cliente se desconectou do Servidor!");
        System.out.println("Cliente finalizado!");
    }
}
