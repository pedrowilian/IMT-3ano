import java.io.*;
import java.net.*;
import java.util.Scanner;

public class AuctionClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5011;
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  CLIENTE DE LEILÃO");
        System.out.println("======================================\n");
        
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Conectado ao leilão!\n");
            
            Thread receiverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("\nDesconectado do leilão.");
                }
            });
            receiverThread.setDaemon(true);
            receiverThread.start();
            
            Thread.sleep(100);
            
            String message;
            while (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.println(message);
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
