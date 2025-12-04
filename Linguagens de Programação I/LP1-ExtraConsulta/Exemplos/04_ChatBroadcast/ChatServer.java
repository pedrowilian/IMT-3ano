import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5003;
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat Broadcast iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new ChatHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    // Adicionar cliente à lista
    public static synchronized void addClient(PrintWriter writer) {
        clients.add(writer);
    }

    // Remover cliente da lista
    public static synchronized void removeClient(PrintWriter writer) {
        clients.remove(writer);
    }

    // Enviar mensagem para todos os clientes
    public static synchronized void broadcast(String message, PrintWriter sender) {
        for (PrintWriter client : clients) {
            if (client != sender) {
                client.println(message);
            }
        }
    }
}

class ChatHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private String clientId;

    public ChatHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Identificar cliente
            clientId = "Cliente_" + socket.getPort();
            ChatServer.addClient(out);
            
            System.out.println(clientId + " entrou no chat");
            ChatServer.broadcast(clientId + " entrou no chat", out);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("SAIR")) {
                    break;
                }
                
                String fullMessage = clientId + " disse: " + message;
                System.out.println(fullMessage);
                ChatServer.broadcast(fullMessage, out);
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        } finally {
            if (out != null) {
                ChatServer.removeClient(out);
                ChatServer.broadcast(clientId + " saiu do chat", out);
                System.out.println(clientId + " saiu do chat");
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
