import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class AuctionServer {
    private static final int PORT = 5011;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static String currentItem = "Quadro Antigo";
    private static double currentBid = 0.0;
    private static String currentWinner = "Ninguém";
    private static boolean auctionActive = true;
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  SERVIDOR DE LEILÃO EM TEMPO REAL");
        System.out.println("  Porta: " + PORT);
        System.out.println("======================================\n");
        System.out.println("Item em leilão: " + currentItem);
        System.out.println("Lance inicial: R$ " + currentBid + "\n");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Aguardando participantes...\n");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
    
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("SERVER: Bem-vindo ao leilão!");
                out.println("SERVER: Digite seu nome:");
                clientName = in.readLine();
                
                if (clientName == null || clientName.trim().isEmpty()) {
                    socket.close();
                    return;
                }
                
                clientName = clientName.trim();
                clients.put(clientName, this);
                
                System.out.println("[+] " + clientName + " entrou no leilão");
                broadcast("SERVER: " + clientName + " entrou no leilão", null);
                
                out.println("SERVER: Item: " + currentItem);
                out.println("SERVER: Lance atual: R$ " + currentBid + " por " + currentWinner);
                out.println("SERVER: Comandos: LANCE <valor> | /status | /encerrar (admin)");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.trim().isEmpty()) continue;
                    
                    System.out.println("[" + clientName + "]: " + message);
                    
                    if (message.toUpperCase().startsWith("LANCE ")) {
                        handleBid(message);
                    } else if (message.equalsIgnoreCase("/status")) {
                        sendStatus();
                    } else if (message.equalsIgnoreCase("/encerrar") || message.equalsIgnoreCase("ENCERRAR")) {
                        if (auctionActive) {
                            endAuction();
                        }
                    } else {
                        out.println("SERVER: Comando inválido. Use: LANCE <valor>");
                    }
                }
                
            } catch (IOException e) {
                System.out.println("[-] " + clientName + " desconectou");
            } finally {
                disconnect();
            }
        }
        
        private void handleBid(String message) {
            if (!auctionActive) {
                out.println("SERVER: Leilão já foi encerrado!");
                return;
            }
            
            try {
                String[] parts = message.split(" ");
                double bid = Double.parseDouble(parts[1]);
                
                synchronized (AuctionServer.class) {
                    if (bid > currentBid) {
                        currentBid = bid;
                        currentWinner = clientName;
                        
                        String announcement = "NOVO_LANCE: R$ " + currentBid + " por " + clientName;
                        System.out.println("  >> " + announcement);
                        broadcast(announcement, null);
                    } else {
                        out.println("SERVER: Lance muito baixo! Mínimo: R$ " + (currentBid + 1));
                    }
                }
            } catch (Exception e) {
                out.println("SERVER: Formato inválido! Use: LANCE <valor>");
            }
        }
        
        private void sendStatus() {
            out.println("=== STATUS DO LEILÃO ===");
            out.println("Item: " + currentItem);
            out.println("Lance atual: R$ " + currentBid);
            out.println("Vencedor atual: " + currentWinner);
            out.println("Participantes: " + clients.size());
            out.println("========================");
        }
        
        private synchronized void endAuction() {
            auctionActive = false;
            String result = "LEILAO_FINALIZADO: Vencedor=" + currentWinner + " com R$ " + currentBid;
            System.out.println("\n" + result);
            broadcast(result, null);
            broadcast("SERVER: Leilão encerrado! Obrigado pela participação.", null);
        }
        
        private void broadcast(String message, String excludeName) {
            for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
                if (excludeName == null || !entry.getKey().equals(excludeName)) {
                    entry.getValue().out.println(message);
                }
            }
        }
        
        private void disconnect() {
            if (clientName != null) {
                clients.remove(clientName);
                broadcast("SERVER: " + clientName + " saiu do leilão", null);
                System.out.println("[-] " + clientName + " saiu");
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {}
        }
    }
}
