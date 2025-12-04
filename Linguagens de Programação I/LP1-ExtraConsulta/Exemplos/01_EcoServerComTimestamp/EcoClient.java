import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EcoClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao servidor Eco com Timestamp");
            System.out.println("Digite mensagens (ou 'sair' para encerrar):");

            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();
                
                if (userInput.equalsIgnoreCase("sair")) {
                    break;
                }
                
                out.println(userInput);
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
