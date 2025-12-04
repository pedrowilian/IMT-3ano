import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class VotingServer {
    private static final int PORT = 5014;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static Map<String, Integer> votes = new ConcurrentHashMap<>();
    private static String question = "Qual sua linguagem favorita?";
    private static String[] options = {"A) Java", "B) Python", "C) JavaScript", "D) C++"};
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE VOTAÇÃO ===");
        votes.put("A", 0);
        votes.put("B", 0);
        votes.put("C", 0);
        votes.put("D", 0);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Porta: " + PORT + "\n");
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
        private String voterName;
        private boolean hasVoted = false;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("SERVER: Nome:");
                voterName = in.readLine();
                clients.put(voterName, this);
                
                out.println("SERVER: " + question);
                for (String opt : options) {
                    out.println("SERVER: " + opt);
                }
                out.println("SERVER: Use: VOTO A (ou B, C, D) | /resultado");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.toUpperCase().startsWith("VOTO ")) {
                        handleVote(message);
                    } else if (message.equalsIgnoreCase("/resultado")) {
                        sendResults();
                    }
                }
            } catch (IOException e) {
            } finally {
                if (voterName != null) clients.remove(voterName);
                try { socket.close(); } catch (IOException e) {}
            }
        }
        
        private synchronized void handleVote(String message) {
            if (hasVoted) {
                out.println("SERVER: Voce ja votou!");
                return;
            }
            
            String vote = message.substring(5).trim().toUpperCase();
            if (votes.containsKey(vote)) {
                votes.put(vote, votes.get(vote) + 1);
                hasVoted = true;
                out.println("SERVER: Voto registrado!");
                System.out.println("[" + voterName + " votou em " + vote + "]");
            } else {
                out.println("SERVER: Opção inválida!");
            }
        }
        
        private void sendResults() {
            broadcast("=== RESULTADO DA VOTAÇÃO ===");
            broadcast(question);
            for (String key : new String[]{"A", "B", "C", "D"}) {
                broadcast(key + " = " + votes.get(key) + " votos");
            }
            broadcast("========================");
        }
        
        private void broadcast(String message) {
            for (ClientHandler client : clients.values()) {
                client.out.println(message);
            }
        }
    }
}
