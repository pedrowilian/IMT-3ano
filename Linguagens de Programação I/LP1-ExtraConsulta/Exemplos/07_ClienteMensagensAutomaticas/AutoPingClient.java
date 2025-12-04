import java.io.*;
import java.net.*;

public class AutoPingClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5006;
    private static final int MAX_MESSAGES = 10;
    private static final int DELAY_MS = 2000; // 2 segundos

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Conectado ao servidor Ping-Pong");
            System.out.println("Enviando mensagens automáticas a cada 2 segundos...\n");

            for (int i = 1; i <= MAX_MESSAGES; i++) {
                // Enviar ping
                out.println("ping");
                System.out.println("[" + i + "/" + MAX_MESSAGES + "] Enviado: ping");
                
                // Receber resposta
                String response = in.readLine();
                System.out.println("[" + i + "/" + MAX_MESSAGES + "] Recebido: " + response);
                System.out.println();
                
                // Aguardar 2 segundos antes da próxima mensagem (exceto na última)
                if (i < MAX_MESSAGES) {
                    Thread.sleep(DELAY_MS);
                }
            }
            
            System.out.println("10 mensagens enviadas. Encerrando conexão.");
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread interrompida: " + e.getMessage());
        }
    }
}
