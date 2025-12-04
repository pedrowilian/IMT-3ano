import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MuteServer {
    private static final int PORT = 5019;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR CHAT COM SILENCIAMENTO ===\nPorta: " + PORT + "\n");
        
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
                
                out.println("SERVER: Digite seu nickname:");
                clientName = in.readLine();
                clients.put(clientName, this);
                
                broadcast("*** " + clientName + " entrou no chat ***", null);
                out.println("SERVER: Use /mute <user> para silenciar (lado cliente)");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/users")) {
                        out.println("SERVER: Usuários: " + String.join(", ", clients.keySet()));
                    } else {
                        broadcast(clientName + ": " + message, clientName);
                    }
                }
            } catch (IOException e) {
            } finally {
                if (clientName != null) {
                    clients.remove(clientName);
                    broadcast("*** " + clientName + " saiu do chat ***", null);
                }
                try { socket.close(); } catch (IOException e) {}
            }
        }
        
        private void broadcast(String message, String sender) {
            for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
                entry.getValue().out.println("FROM:" + (sender == null ? "SERVER" : sender) + ":" + message);
            }
            System.out.println(message);
        }
    }
}
