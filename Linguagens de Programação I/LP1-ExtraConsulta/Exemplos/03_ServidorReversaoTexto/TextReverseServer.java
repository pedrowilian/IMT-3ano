import java.io.*;
import java.net.*;

public class TextReverseServer {
    private static final int PORT = 5002;

    public static void main(String[] args) {
        System.out.println("Servidor de Reversão de Texto iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new ReverseHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class ReverseHandler implements Runnable {
    private Socket socket;

    public ReverseHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("FIM")) {
                    out.println("Conexão encerrada.");
                    break;
                }
                
                String reversed = new StringBuilder(message).reverse().toString();
                out.println(reversed);
                System.out.println("Recebido: " + message + " | Enviado: " + reversed);
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
