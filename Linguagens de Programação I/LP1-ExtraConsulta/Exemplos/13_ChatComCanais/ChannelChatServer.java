import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChannelChatServer {
    private static final int PORT = 5012;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static Map<String, Set<String>> channels = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  SERVIDOR DE CHAT COM CANAIS");
        System.out.println("  Porta: " + PORT);
        System.out.println("======================================\n");
        
        // Criar canais padrão
        channels.put("geral", ConcurrentHashMap.newKeySet());
        channels.put("games", ConcurrentHashMap.newKeySet());
        channels.put("tech", ConcurrentHashMap.newKeySet());
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Canais disponíveis: geral, games, tech\n");
            System.out.println("Aguardando conexões...\n");
            
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
        private String currentChannel = "geral";
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("SERVER: Digite seu nickname:");
                nickname = in.readLine();
                
                if (nickname == null || nickname.trim().isEmpty()) {
                    socket.close();
                    return;
                }
                
                nickname = nickname.trim();
                clients.put(nickname, this);
                
                // Adicionar ao canal geral por padrão
                channels.get("geral").add(nickname);
                
                System.out.println("[+] " + nickname + " entrou (canal: geral)");
                
                out.println("SERVER: Bem-vindo, " + nickname + "!");
                out.println("SERVER: Você está no canal: #" + currentChannel);
                out.println("SERVER: Comandos: /join <canal> | /channels | /users");
                
                broadcastToChannel(currentChannel, "SERVER: " + nickname + " entrou no canal", nickname);
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.trim().isEmpty()) continue;
                    
                    System.out.println("[" + nickname + "@" + currentChannel + "]: " + message);
                    
                    if (message.startsWith("/join ")) {
                        handleJoin(message);
                    } else if (message.equalsIgnoreCase("/channels")) {
                        listChannels();
                    } else if (message.equalsIgnoreCase("/users")) {
                        listUsers();
                    } else if (message.equalsIgnoreCase("/quit")) {
                        break;
                    } else {
                        // Enviar mensagem para o canal atual
                        broadcastToChannel(currentChannel, "[" + nickname + "]: " + message, null);
                    }
                }
                
            } catch (IOException e) {
                System.out.println("[-] " + nickname + " desconectou");
            } finally {
                disconnect();
            }
        }
        
        private void handleJoin(String command) {
            String[] parts = command.split(" ");
            if (parts.length < 2) {
                out.println("SERVER: Use: /join <canal>");
                return;
            }
            
            String newChannel = parts[1].toLowerCase();
            
            // Criar canal se não existir
            if (!channels.containsKey(newChannel)) {
                channels.put(newChannel, ConcurrentHashMap.newKeySet());
                System.out.println("[Canal criado: " + newChannel + "]");
            }
            
            // Remover do canal atual
            channels.get(currentChannel).remove(nickname);
            broadcastToChannel(currentChannel, "SERVER: " + nickname + " saiu do canal", null);
            
            // Adicionar ao novo canal
            currentChannel = newChannel;
            channels.get(currentChannel).add(nickname);
            
            out.println("SERVER: Você entrou no canal #" + currentChannel);
            System.out.println("[" + nickname + " -> #" + currentChannel + "]");
            
            broadcastToChannel(currentChannel, "SERVER: " + nickname + " entrou no canal", nickname);
        }
        
        private void listChannels() {
            out.println("=== CANAIS DISPONIVEIS ===");
            for (Map.Entry<String, Set<String>> entry : channels.entrySet()) {
                String marker = entry.getKey().equals(currentChannel) ? " (voce esta aqui)" : "";
                out.println("  #" + entry.getKey() + " [" + entry.getValue().size() + " usuarios]" + marker);
            }
            out.println("==========================");
        }
        
        private void listUsers() {
            out.println("=== USUARIOS EM #" + currentChannel + " ===");
            for (String user : channels.get(currentChannel)) {
                String marker = user.equals(nickname) ? " (voce)" : "";
                out.println("  - " + user + marker);
            }
            out.println("==========================");
        }
        
        private void broadcastToChannel(String channel, String message, String excludeNickname) {
            Set<String> channelUsers = channels.get(channel);
            if (channelUsers != null) {
                for (String user : channelUsers) {
                    if (excludeNickname == null || !user.equals(excludeNickname)) {
                        ClientHandler client = clients.get(user);
                        if (client != null) {
                            client.out.println(message);
                        }
                    }
                }
            }
        }
        
        private void disconnect() {
            if (nickname != null) {
                clients.remove(nickname);
                if (currentChannel != null && channels.containsKey(currentChannel)) {
                    channels.get(currentChannel).remove(nickname);
                    broadcastToChannel(currentChannel, "SERVER: " + nickname + " saiu do chat", null);
                }
                System.out.println("[-] " + nickname + " saiu");
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {}
        }
    }
}
