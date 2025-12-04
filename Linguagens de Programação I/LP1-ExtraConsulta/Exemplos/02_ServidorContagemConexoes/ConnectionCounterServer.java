import java.io.*;
import java.net.*;

public class ConnectionCounterServer {
    private static final int PORT = 5001;
    private static int connectionCounter = 0;

    public static void main(String[] args) {
        System.out.println("Servidor de Contagem de Conexões iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                
                // Criar uma thread para cada cliente
                new Thread(new ConnectionHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    // Método sincronizado para incrementar e retornar o contador
    public static synchronized int getNextConnectionNumber() {
        return ++connectionCounter;
    }
}

class ConnectionHandler implements Runnable {
    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            int clientNumber = ConnectionCounterServer.getNextConnectionNumber();
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Você é o cliente número " + clientNumber);
            
            System.out.println("Cliente #" + clientNumber + " conectado: " + socket.getInetAddress());
            
            socket.close();
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        }
    }
}
