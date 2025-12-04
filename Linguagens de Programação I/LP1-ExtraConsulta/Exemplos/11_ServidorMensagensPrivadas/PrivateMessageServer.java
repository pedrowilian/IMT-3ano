import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PrivateMessageServer {
    private static final int PORT = 5010;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  SERVIDOR DE MENSAGENS PRIVADAS");
        System.out.println("  Porta: " + PORT);
        System.out.println("======================================\n");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado! Aguardando conexões...\n");
            
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
        private String nickname;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Solicitar nickname
                out.println("SERVER: Digite seu nickname:");
                nickname = in.readLine();
                
                if (nickname == null || nickname.trim().isEmpty()) {
                    out.println("SERVER: Nickname inválido. Desconectando...");
                    socket.close();
                    return;
                }
                
                nickname = nickname.trim();
                
                // Verificar se nickname já existe
                if (clients.containsKey(nickname)) {
                    out.println("SERVER: Nickname já está em uso. Desconectando...");
                    socket.close();
                    return;
                }
                
                // Registrar cliente
                clients.put(nickname, this);
                System.out.println("[+] " + nickname + " entrou no chat (" + socket.getInetAddress() + ")");
                out.println("SERVER: Bem-vindo, " + nickname + "!");
                out.println("SERVER: Use @username mensagem para mensagem privada");
                out.println("SERVER: Mensagens sem @ são enviadas para todos");
                
                // Notificar todos sobre novo usuário
                broadcast("SERVER: " + nickname + " entrou no chat", nickname);
                
                // Enviar lista de usuários
                sendUserList();
                
                // Processar mensagens
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.trim().isEmpty()) continue;
                    
                    System.out.println("[" + nickname + "]: " + message);
                    
                    if (message.startsWith("@")) {
                        // Mensagem privada
                        handlePrivateMessage(message);
                    } else if (message.equalsIgnoreCase("/users") || message.equalsIgnoreCase("/list")) {
                        // Listar usuários
                        sendUserList();
                    } else if (message.equalsIgnoreCase("/quit") || message.equalsIgnoreCase("/exit")) {
                        // Sair
                        break;
                    } else {
                        // Broadcast
                        broadcast("[" + nickname + "]: " + message, null);
                    }
                }
                
            } catch (IOException e) {
                System.out.println("[-] " + nickname + " desconectou (erro)");
            } finally {
                disconnect();
            }
        }
        
        private void handlePrivateMessage(String message) {
            // Formato: @username mensagem
            int spaceIndex = message.indexOf(' ');
            if (spaceIndex == -1) {
                out.println("SERVER: Formato inválido. Use: @username mensagem");
                return;
            }
            
            String targetNickname = message.substring(1, spaceIndex);
            String privateMessage = message.substring(spaceIndex + 1);
            
            ClientHandler targetClient = clients.get(targetNickname);
            
            if (targetClient == null) {
                out.println("SERVER: Usuário '" + targetNickname + "' não encontrado");
            } else {
                // Enviar para destinatário
                targetClient.out.println("[PRIVADO de " + nickname + "]: " + privateMessage);
                // Confirmar para remetente
                out.println("[PRIVADO para " + targetNickname + "]: " + privateMessage);
                System.out.println("  -> Mensagem privada de " + nickname + " para " + targetNickname);
            }
        }
        
        private void sendUserList() {
            out.println("SERVER: ===== Usuários Online =====");
            for (String user : clients.keySet()) {
                if (user.equals(nickname)) {
                    out.println("SERVER:   - " + user + " (você)");
                } else {
                    out.println("SERVER:   - " + user);
                }
            }
            out.println("SERVER: ===========================");
        }
        
        private void broadcast(String message, String excludeNickname) {
            for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
                if (excludeNickname == null || !entry.getKey().equals(excludeNickname)) {
                    entry.getValue().out.println(message);
                }
            }
        }
        
        private void disconnect() {
            if (nickname != null) {
                clients.remove(nickname);
                broadcast("SERVER: " + nickname + " saiu do chat", null);
                System.out.println("[-] " + nickname + " saiu do chat");
            }
            
            try {
                if (socket != null) socket.close();
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
