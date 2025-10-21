import java.io.*;
import java.net.*;

public class SimpleServerTest {
    public static void main(String[] args) {
        try {
            // Cria um servidor socket na porta 6789
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Servidor aguardando conexão na porta 6789...");
            
            // Aguarda conexão do cliente
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");
            
            // Cria buffers de entrada e saída
            BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream())
            );
            DataOutputStream outToClient = new DataOutputStream(
                connectionSocket.getOutputStream()
            );
            
                // Loop para manter a conexão ativa
            String clientSentence;
            while (true) {
                // Lê mensagem do cliente
                clientSentence = inFromClient.readLine();
                
                // Verifica se o cliente encerrou a conexão
                if (clientSentence == null) {
                    System.out.println("Cliente desconectou.");
                    break;
                }
                
                System.out.println("Recebido do cliente: " + clientSentence);
                
                // Verifica se é o comando de encerramento
                if (clientSentence.equals("-1")) {
                    System.out.println("Comando de encerramento recebido. Fechando servidor...");
                    outToClient.writeBytes("Conexão encerrada.\n");
                    break;
                }
                
                // Processa a mensagem (converte para maiúsculas)
                String capitalizedSentence = clientSentence.toUpperCase() + "\n";
                
                // Envia resposta ao cliente
                outToClient.writeBytes(capitalizedSentence);
                System.out.println("Resposta enviada ao cliente: " + capitalizedSentence.trim());
            }
            
            // Fecha conexões
            connectionSocket.close();
            serverSocket.close();
            System.out.println("Servidor encerrado.");
            
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
