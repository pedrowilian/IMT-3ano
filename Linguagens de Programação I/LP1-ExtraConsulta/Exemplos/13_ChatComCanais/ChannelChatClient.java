import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChannelChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5012);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("=== CHAT COM CANAIS ===\n");
            
            Thread receiverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {}
            });
            receiverThread.setDaemon(true);
            receiverThread.start();
            
            Thread.sleep(100);
            
            String message;
            while (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.println(message);
                if (message.equalsIgnoreCase("/quit")) break;
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
