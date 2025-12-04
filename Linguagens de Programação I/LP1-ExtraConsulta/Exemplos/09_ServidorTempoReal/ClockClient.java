import java.io.*;
import java.net.*;

public class ClockClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5008;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao Servidor de Tempo Real");
            System.out.println("(Pressione Ctrl+C para encerrar)\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
            
            socket.close();
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
