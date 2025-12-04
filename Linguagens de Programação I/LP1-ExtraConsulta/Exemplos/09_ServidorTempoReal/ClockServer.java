import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClockServer {
    private static final int PORT = 5008;
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Tempo Real iniciado na porta " + PORT);
        
        // Thread para broadcast periódico de tempo
        new Thread(new TimeBroadcaster()).start();
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new ClockHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    public static synchronized void addClient(PrintWriter writer) {
        clients.add(writer);
    }

    public static synchronized void removeClient(PrintWriter writer) {
        clients.remove(writer);
    }

    public static synchronized void broadcastTime(String time) {
        for (PrintWriter client : clients) {
            client.println("Hora atual: " + time);
        }
    }
}

class TimeBroadcaster implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Aguardar 1 segundo
                
                String currentTime = LocalTime.now().format(
                    DateTimeFormatter.ofPattern("HH:mm:ss")
                );
                ClockServer.broadcastTime(currentTime);
            } catch (InterruptedException e) {
                System.err.println("Thread de broadcast interrompida: " + e.getMessage());
                break;
            }
        }
    }
}

class ClockHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;

    public ClockHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            ClockServer.addClient(out);
            
            out.println("Conectado ao Servidor de Tempo Real");
            out.println("Você receberá a hora atual a cada segundo.");
            
            // Manter a conexão ativa
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equalsIgnoreCase("SAIR")) {
                    break;
                }
            }
        } catch (IOException e) {
            // Cliente desconectado
        } finally {
            if (out != null) {
                ClockServer.removeClient(out);
            }
            try {
                socket.close();
                System.out.println("Cliente desconectado");
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
