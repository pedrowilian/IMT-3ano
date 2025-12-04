import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TextReverseClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5002;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao Servidor de Reversão de Texto");
            System.out.println("Digite frases para inverter (ou 'FIM' para encerrar):");

            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();
                
                out.println(userInput);
                
                if (userInput.equalsIgnoreCase("FIM")) {
                    String response = in.readLine();
                    System.out.println(response);
                    break;
                }
                
                String response = in.readLine();
                System.out.println("Resposta: " + response);
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
