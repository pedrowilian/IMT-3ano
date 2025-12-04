import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MathClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5004;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao Servidor de Operações Matemáticas");
            System.out.println("Comandos disponíveis:");
            System.out.println("  SOMA <num1> <num2>");
            System.out.println("  SUB <num1> <num2>");
            System.out.println("  MULT <num1> <num2>");
            System.out.println("  DIV <num1> <num2>");
            System.out.println("  SAIR");
            System.out.println();

            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();
                
                out.println(userInput);
                
                if (userInput.equalsIgnoreCase("SAIR")) {
                    String response = in.readLine();
                    System.out.println(response);
                    break;
                }
                
                String response = in.readLine();
                System.out.println("Resultado: " + response);
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
