import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NicknameChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5007;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao servidor de chat com nickname");

            // Thread para receber mensagens do servidor
            new Thread(new MessageListener(socket)).start();

            // Thread principal para enviar mensagens
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            // Receber e responder solicitação de nome
            String prompt = in.readLine();
            System.out.println(prompt);
            String nickname = scanner.nextLine();
            out.println(nickname);

            // Loop de mensagens
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

class MessageListener implements Runnable {
    private Socket socket;

    public MessageListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            
            // Pular a primeira linha (prompt de nome) pois já foi lida na thread principal
            in.readLine();
            
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            // Conexão encerrada
        }
    }
}
