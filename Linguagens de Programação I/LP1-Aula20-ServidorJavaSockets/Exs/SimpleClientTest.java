import java.io.*;
import java.net.*;

public class SimpleClientTest {
    public static void main(String[] args) {
        try {
            // Lê entrada do usuário
            BufferedReader inFromUser = new BufferedReader(
                new InputStreamReader(System.in)
            );
            
            // Cria socket de conexão com o servidor
            Socket clientSocket = new Socket("localhost", 6789);
            System.out.println("Conectado ao servidor!");
            
            // Cria buffers de saída e entrada
            DataOutputStream outToServer = new DataOutputStream(
                clientSocket.getOutputStream()
            );
            BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );
            
            // Loop para manter a conexão ativa
            String sentence;
            String modifiedSentence;
            
            while (true) {
                // Solicita entrada do usuário
                System.out.print("Digite uma mensagem (ou -1 para sair): ");
                sentence = inFromUser.readLine();
                
                // Envia mensagem ao servidor
                outToServer.writeBytes(sentence + "\n");
                
                // Verifica se é o comando de encerramento
                if (sentence.equals("-1")) {
                    modifiedSentence = inFromServer.readLine();
                    System.out.println("Servidor: " + modifiedSentence);
                    System.out.println("Encerrando conexão...");
                    break;
                }
                
                // Recebe resposta do servidor
                modifiedSentence = inFromServer.readLine();
                System.out.println("Resposta do servidor: " + modifiedSentence);
            }
            
            // Fecha conexão
            clientSocket.close();
            System.out.println("Cliente desconectado.");
            
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
