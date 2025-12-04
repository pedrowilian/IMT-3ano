import java.io.*;
import java.net.*;

public class ConnectionCounterClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5001;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String message = in.readLine();
            System.out.println(message);
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
