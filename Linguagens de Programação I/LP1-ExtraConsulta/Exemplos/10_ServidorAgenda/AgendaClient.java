import java.io.*;
import java.net.*;
import java.util.Scanner;

public class AgendaClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5009;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            // Ler mensagens de boas-vindas
            System.out.println(in.readLine());
            System.out.println(in.readLine());
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
                
                // Ler resposta (pode ser multilinha para LIST)
                String response = in.readLine();
                System.out.println(response);
                
                // Se for comando LIST, ler todas as linhas até encontrar a linha de fechamento
                if (userInput.trim().equalsIgnoreCase("LIST") && response.startsWith("===")) {
                    String line;
                    while ((line = in.readLine()) != null && !line.startsWith("===")) {
                        System.out.println(line);
                    }
                    if (line != null) {
                        System.out.println(line);
                    }
                }
                
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
