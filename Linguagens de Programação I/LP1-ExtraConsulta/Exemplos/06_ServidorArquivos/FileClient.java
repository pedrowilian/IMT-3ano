import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5005;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao Servidor de Arquivos");
            System.out.println("Comandos:");
            System.out.println("  GET <nome_arquivo>");
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

                if (response.equals("INICIO_ARQUIVO")) {
                    System.out.println("\n--- Conteudo do arquivo ---");
                    String line;
                    while (!(line = in.readLine()).equals("FIM_ARQUIVO")) {
                        System.out.println(line);
                    }
                    System.out.println("--- Fim do arquivo ---\n");
                } else {
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
