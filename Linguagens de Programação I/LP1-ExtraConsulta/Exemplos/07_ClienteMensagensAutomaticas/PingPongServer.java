import java.io.*;
import java.net.*;

public class PingPongServer {
    private static final int PORT = 5006;

    public static void main(String[] args) {
        System.out.println("Servidor Ping-Pong iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new PingPongHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class PingPongHandler implements Runnable {
    private Socket socket;

    public PingPongHandler(Socket socket) {
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
                System.out.println("Recebido: " + message);
                
                if (message.equalsIgnoreCase("ping")) {
                    out.println("pong");
                } else {
                    out.println("Comando não reconhecido");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("Cliente desconectado");
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
