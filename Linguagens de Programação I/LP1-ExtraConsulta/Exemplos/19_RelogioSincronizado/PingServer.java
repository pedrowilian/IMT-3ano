import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PingServer {
    private static final int PORT = 5018;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR PING SINCRONIZADO ===\nPorta: " + PORT + "\n");
        
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
                
                out.println("SERVER: Nome:");
                clientName = in.readLine();
                clients.put(clientName, this);
                
                out.println("SERVER: Envie PING com timestamp ou comando TIME");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.toUpperCase().startsWith("PING ")) {
                        try {
                            long clientTime = Long.parseLong(message.substring(5).trim());
                            long serverTime = System.currentTimeMillis();
                            long latency = serverTime - clientTime;
                            
                            String result = clientName + " - Latência: " + latency + "ms";
                            broadcast(result);
                            System.out.println(result);
                        } catch (NumberFormatException e) {}
                    } else if (message.equalsIgnoreCase("TIME")) {
                        long serverTime = System.currentTimeMillis();
                        out.println("SERVER_TIME: " + serverTime);
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
