import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class LogServer {
    private static final int PORT = 5016;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE LOGS ===\nPorta: " + PORT + "\n");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private String clientName;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("SERVER: Nome do agente:");
                clientName = in.readLine();
                clients.put(clientName, this);
                
                out.println("SERVER: Envie logs no formato: LOG <agente> <info>");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.toUpperCase().startsWith("LOG ")) {
                        String logEntry = message.substring(4).trim();
                        String timestamp = java.time.LocalTime.now().format(
                            java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
                        String fullLog = "[" + timestamp + "] " + logEntry;
                        broadcast(fullLog);
                        System.out.println(fullLog);
                    }
                }
            } catch (IOException e) {
            } finally {
                if (clientName != null) clients.remove(clientName);
                try { socket.close(); } catch (IOException e) {}
            }
        }
        
        private void broadcast(String message) {
            for (ClientHandler client : clients.values()) {
                client.out.println(message);
            }
        }
    }
}
