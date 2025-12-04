import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class HangmanServer {
    private static final int PORT = 5013;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static String secretWord = "PROGRAMACAO";
    private static char[] currentState;
    private static Set<Character> usedLetters = ConcurrentHashMap.newKeySet();
    private static Set<Character> wrongLetters = ConcurrentHashMap.newKeySet();
    private static int errors = 0;
    private static final int MAX_ERRORS = 6;
    private static boolean gameActive = true;
    private static String winner = null;
    
    public static void main(String[] args) {
        System.out.println("=== JOGO DA FORCA MULTIPLAYER ===");
        System.out.println("Porta: " + PORT);
        System.out.println("Palavra secreta: " + secretWord + "\n");
        
        currentState = new char[secretWord.length()];
        Arrays.fill(currentState, '_');
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Aguardando jogadores...\n");
            
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
        private String playerName;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("SERVER: Digite seu nome:");
                playerName = in.readLine();
                if (playerName == null || playerName.trim().isEmpty()) {
                    socket.close();
                    return;
                }
                
                playerName = playerName.trim();
                clients.put(playerName, this);
                System.out.println("[+] " + playerName + " entrou no jogo");
                
                broadcast("SERVER: " + playerName + " entrou no jogo", null);
                sendGameState();
                
                String message;
                while ((message = in.readLine()) != null) {
                    message = message.trim().toUpperCase();
                    
                    if (message.startsWith("LETRA ") && message.length() == 7) {
                        char letter = message.charAt(6);
                        handleGuess(letter);
                    } else if (message.equalsIgnoreCase("/status")) {
                        sendGameState();
                    }
                }
                
            } catch (IOException e) {
                System.out.println("[-] " + playerName + " desconectou");
            } finally {
                if (playerName != null) clients.remove(playerName);
                try { socket.close(); } catch (IOException e) {}
            }
        }
        
        private synchronized void handleGuess(char letter) {
            if (!gameActive) {
                out.println("SERVER: O jogo já terminou! Vencedor: " + winner);
                return;
            }
            
            if (usedLetters.contains(letter)) {
                out.println("SERVER: Letra já foi usada!");
                return;
            }
            
            usedLetters.add(letter);
            boolean found = false;
            
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    currentState[i] = letter;
                    found = true;
                }
            }
            
            if (!found) {
                wrongLetters.add(letter);
                errors++;
            }
            
            String state = "ATUAL: " + new String(currentState);
            String wrong = "ERRADAS: " + wrongLetters;
            
            broadcast(state, null);
            broadcast(wrong, null);
            broadcast("SERVER: " + playerName + " tentou a letra " + letter, null);
            
            // Verificar vitória
            if (new String(currentState).equals(secretWord)) {
                gameActive = false;
                winner = playerName;
                broadcast("VENCEDOR: " + playerName + " completou a palavra!", null);
                System.out.println("*** VENCEDOR: " + playerName + " ***");
            } else if (errors >= MAX_ERRORS) {
                gameActive = false;
                broadcast("GAME_OVER: A palavra era " + secretWord, null);
                System.out.println("*** GAME OVER ***");
            }
            
            System.out.println("  " + state + " | " + wrong + " | Erros: " + errors);
        }
        
        private void sendGameState() {
            out.println("=== ESTADO DO JOGO ===");
            out.println("ATUAL: " + new String(currentState));
            out.println("ERRADAS: " + wrongLetters);
            out.println("Erros: " + errors + "/" + MAX_ERRORS);
            out.println("====================");
        }
        
        private void broadcast(String message, String exclude) {
            for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
                if (exclude == null || !entry.getKey().equals(exclude)) {
                    entry.getValue().out.println(message);
                }
            }
        }
    }
}
