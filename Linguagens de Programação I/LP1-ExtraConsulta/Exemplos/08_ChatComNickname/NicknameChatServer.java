import java.io.*;
import java.net.*;
import java.util.*;

public class NicknameChatServer {
    private static final int PORT = 5007;
    private static Map<String, PrintWriter> clients = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat com Nickname iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nova conexão: " + clientSocket.getInetAddress());
                
                new Thread(new NicknameChatHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    public static synchronized void addClient(String nickname, PrintWriter writer) {
        clients.put(nickname, writer);
    }

    public static synchronized void removeClient(String nickname) {
        clients.remove(nickname);
    }

    public static synchronized void broadcast(String message, String senderNickname) {
        for (Map.Entry<String, PrintWriter> entry : clients.entrySet()) {
            if (!entry.getKey().equals(senderNickname)) {
                entry.getValue().println(message);
            }
        }
    }

    public static synchronized void broadcastToAll(String message) {
        for (PrintWriter client : clients.values()) {
            client.println(message);
        }
    }
}

class NicknameChatHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private String nickname;

    public NicknameChatHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Solicitar nickname
            out.println("Digite seu nome:");
            nickname = in.readLine();
            
            if (nickname == null || nickname.trim().isEmpty()) {
                nickname = "Anônimo_" + socket.getPort();
            }
            
            NicknameChatServer.addClient(nickname, out);
            
            System.out.println(nickname + " entrou no chat");
            NicknameChatServer.broadcast(nickname + " entrou no chat", nickname);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("SAIR")) {
                    break;
                }
                
                String fullMessage = "[" + nickname + "] " + message;
                System.out.println(fullMessage);
                NicknameChatServer.broadcast(fullMessage, nickname);
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        } finally {
            if (nickname != null) {
                NicknameChatServer.removeClient(nickname);
                NicknameChatServer.broadcastToAll(nickname + " saiu do chat");
                System.out.println(nickname + " saiu do chat");
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
