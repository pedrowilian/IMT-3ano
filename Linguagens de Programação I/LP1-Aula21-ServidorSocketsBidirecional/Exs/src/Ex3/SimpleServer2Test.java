package Ex3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class SimpleServer2Test {
    // Constantes do servidor original
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORTA = 3334;

    private static ServerSocket servidor;
    private static Socket clienteAceito;

    private static Scanner entradaCliente; // Scanner do Socket
    private static PrintStream saidaCliente; // PrintStream do Socket

    // Novo: Scanner para ler do console do servidor
    private static Scanner entradaConsole;

    public static void main(String args[]) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR 2 (com Edição) *v*v*v*");

        // Inicializa o scanner do console
        entradaConsole = new Scanner(System.in);

        try {
            iniciaServidor();
            aguardaConexaoCliente();
            conversaComCliente(); // Lógica modificada está aqui
            encerraConexaoCliente();
        } catch (IOException ex) {
            System.out.println("Erro no Servidor: " + ex.getMessage());
        } finally {
            encerraServidor();
            entradaConsole.close(); // Fecha o scanner do console
        }
    }

    private static void iniciaServidor() throws IOException {
        servidor = new ServerSocket(PORTA);
        System.out.println("Servidor iniciado e escutando a porta " + PORTA);
    }

    private static void aguardaConexaoCliente() throws IOException {
        clienteAceito = servidor.accept();
        System.out.println("Cliente IP " +
                clienteAceito.getInetAddress().getHostAddress() +
                " conectado ao Servidor pela porta " + PORTA);

        // Renomeado para clareza
        entradaCliente = new Scanner(clienteAceito.getInputStream());
        saidaCliente = new PrintStream(clienteAceito.getOutputStream());
    }

    // Método principal modificado
    private static void conversaComCliente() throws IOException {
        String msg;
        while (entradaCliente.hasNextLine()) {
            msg = leMensagemCliente();

            // Em vez de ecoar, chama o método de confirmação
            confirmaEEnviaMensagem(msg);
        }
    }

    private static String leMensagemCliente() throws IOException {
        String msg = entradaCliente.nextLine();
        System.out.print("Chegou do Cliente: ");
        System.out.println(msg);
        return msg;
    }

    // NOVO MÉTODO (substitui o 'retornaMensagemCliente' original)
    private static void confirmaEEnviaMensagem(String msgOriginal) throws IOException {
        System.out.println("-------------------------------------------------");
        System.out.println("Mensagem a ser ecoada: \"" + msgOriginal + "\"");
        System.out.print(">> Digite a mensagem para enviar (ou pressione ENTER para usar a original): ");

        String msgParaEnviar = entradaConsole.nextLine();

        if (msgParaEnviar.isEmpty()) {
            msgParaEnviar = msgOriginal;
            System.out.println("(Usando mensagem original)");
        }

        // Envia a mensagem (original ou editada)
        saidaCliente.println(msgParaEnviar);

        System.out.print("Ecoou ao Cliente: ");
        System.out.println(msgParaEnviar);
        System.out.println("-------------------------------------------------");
    }

    private static void encerraConexaoCliente() throws IOException {
        entradaCliente.close();
        saidaCliente.close();
        System.out.println("Cliente se desconectou do Servidor!");
    }

    private static void encerraServidor() {
        try {
            if (servidor != null && !servidor.isClosed()) {
                servidor.close();
                System.out.println("Servidor finalizado!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao fechar o servidor: " + e.getMessage());
        }
    }
}
