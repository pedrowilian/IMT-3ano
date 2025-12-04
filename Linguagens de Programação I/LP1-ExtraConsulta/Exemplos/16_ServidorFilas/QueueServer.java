import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class QueueServer {
    private static final int PORT = 5015;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static Queue<String> globalQueue = new ConcurrentLinkedQueue<>();
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE FILAS ===\nPorta: " + PORT + "\n");
        
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
                
                out.println("SERVER: Comandos: PUSH <item> | POP | SIZE");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.toUpperCase().startsWith("PUSH ")) {
                        String item = message.substring(5).trim();
                        synchronized (globalQueue) {
                            globalQueue.offer(item);
                        }
                        broadcast(clientName + " adicionou: " + item);
                        System.out.println("[" + clientName + " PUSH: " + item + "] Tamanho: " + globalQueue.size());
                    } else if (message.equalsIgnoreCase("POP")) {
                        synchronized (globalQueue) {
                            String item = globalQueue.poll();
                            if (item != null) {
                                broadcast(clientName + " removeu: " + item);
                                System.out.println("[" + clientName + " POP: " + item + "] Tamanho: " + globalQueue.size());
                            } else {
                                out.println("SERVER: Fila vazia!");
                            }
                        }
                    } else if (message.equalsIgnoreCase("SIZE")) {
                        out.println("SERVER: Tamanho da fila: " + globalQueue.size());
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
