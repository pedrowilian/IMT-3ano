import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SensorServer {
    private static final int PORT = 5020;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static Map<String, SensorData> sensorStates = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 15000; // 15 segundos
    
    static class SensorData {
        String value;
        long lastUpdate;
        
        SensorData(String value) {
            this.value = value;
            this.lastUpdate = System.currentTimeMillis();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE SENSORES ===\nPorta: " + PORT + "\n");
        
        // Thread para broadcast periódico do estado
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000); // A cada 10 segundos
                    broadcastStates();
                } catch (InterruptedException e) {}
            }
        }).start();
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void broadcastStates() {
        long now = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("=== ESTADO DOS SENSORES ===\n");
        
        for (Map.Entry<String, SensorData> entry : sensorStates.entrySet()) {
            String sensor = entry.getKey();
            SensorData data = entry.getValue();
            long elapsed = now - data.lastUpdate;
            
            if (elapsed > TIMEOUT) {
                sb.append(sensor).append(": TIMEOUT (").append(elapsed/1000).append("s)\n");
            } else {
                sb.append(sensor).append(": ").append(data.value).append("\n");
            }
        }
        
        for (ClientHandler client : clients.values()) {
            client.out.println(sb.toString());
        }
        System.out.println(sb.toString());
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
                
                out.println("SERVER: Nome do sensor:");
                clientName = in.readLine();
                clients.put(clientName, this);
                
                out.println("SERVER: Envie dados: TEMP 23.5 ou UMID 60% etc");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.contains(" ")) {
                        String[] parts = message.split(" ", 2);
                        String type = parts[0].toUpperCase();
                        String value = parts[1];
                        
                        String sensorKey = clientName + "_" + type;
                        sensorStates.put(sensorKey, new SensorData(value));
                        
                        System.out.println("[" + sensorKey + "] = " + value);
                    }
                }
            } catch (IOException e) {
            } finally {
                if (clientName != null) clients.remove(clientName);
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}
