import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PrivateMessageClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5010;
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  CLIENTE DE MENSAGENS PRIVADAS");
        System.out.println("======================================\n");
        
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Conectado ao servidor!\n");
            
            // Thread para receber mensagens
            Thread receiverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                        
                        // Se servidor rejeitar, encerrar
                        if (message.contains("Desconectando...")) {
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("\nDesconectado do servidor.");
                }
            });
            receiverThread.setDaemon(true);
            receiverThread.start();
            
            // Aguardar solicitação de nickname
            Thread.sleep(100);
            
            // Enviar mensagens
            String message;
            while (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.println(message);
                
                if (message.equalsIgnoreCase("/quit") || message.equalsIgnoreCase("/exit")) {
                    break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
