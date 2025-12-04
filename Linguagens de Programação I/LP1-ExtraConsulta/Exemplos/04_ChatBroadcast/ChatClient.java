import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5003;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao servidor de chat");
            System.out.println("Digite mensagens (ou 'SAIR' para encerrar):");

            // Thread para receber mensagens do servidor
            new Thread(new MessageReceiver(socket)).start();

            // Thread principal para enviar mensagens
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                out.println(userInput);
                
                if (userInput.equalsIgnoreCase("SAIR")) {
                    break;
                }
            }

            scanner.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}

class MessageReceiver implements Runnable {
    private Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            // Conexão encerrada
        }
    }
}
