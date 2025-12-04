import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class RankingServer {
    private static final int PORT = 5017;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static Map<String, Integer> scores = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE RANKING ===\nPorta: " + PORT + "\n");
        
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
                scores.putIfAbsent(clientName, 0);
                
                out.println("SERVER: Comandos: ADD <pontos> | SUB <pontos> | GET | RANK");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.toUpperCase().startsWith("ADD ")) {
                        try {
                            int points = Integer.parseInt(message.substring(4).trim());
                            scores.put(clientName, scores.get(clientName) + points);
                            broadcast(clientName + " ganhou " + points + " pontos!");
                            System.out.println(clientName + " ADD " + points + " -> Total: " + scores.get(clientName));
                        } catch (NumberFormatException e) {}
                    } else if (message.toUpperCase().startsWith("SUB ")) {
                        try {
                            int points = Integer.parseInt(message.substring(4).trim());
                            scores.put(clientName, Math.max(0, scores.get(clientName) - points));
                            broadcast(clientName + " perdeu " + points + " pontos!");
                            System.out.println(clientName + " SUB " + points + " -> Total: " + scores.get(clientName));
                        } catch (NumberFormatException e) {}
                    } else if (message.equalsIgnoreCase("GET")) {
                        out.println("SERVER: Seus pontos: " + scores.get(clientName));
                    } else if (message.equalsIgnoreCase("RANK")) {
                        broadcastRanking();
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
        
        private void broadcastRanking() {
            List<Map.Entry<String, Integer>> ranking = new ArrayList<>(scores.entrySet());
            ranking.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            
            StringBuilder sb = new StringBuilder("=== RANKING ===\n");
            int pos = 1;
            for (Map.Entry<String, Integer> entry : ranking) {
                sb.append(pos++).append(". ").append(entry.getKey())
                  .append(": ").append(entry.getValue()).append(" pts\n");
            }
            
            for (ClientHandler client : clients.values()) {
                client.out.println(sb.toString());
            }
            System.out.println(sb.toString());
        }
    }
}
