import java.io.*;
import java.net.*;

public class FileServer {
    private static final int PORT = 5005;
    private static final String FILES_DIRECTORY = "./server_files/";

    public static void main(String[] args) {
        // Criar diretorio de arquivos se nao existir
        new File(FILES_DIRECTORY).mkdirs();
        
        System.out.println("Servidor de Arquivos iniciado na porta " + PORT);
        System.out.println("Diretório de arquivos: " + FILES_DIRECTORY);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new FileHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class FileHandler implements Runnable {
    private Socket socket;
    private static final String FILES_DIRECTORY = "./server_files/";

    public FileHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String command;
            while ((command = in.readLine()) != null) {
                if (command.equalsIgnoreCase("SAIR")) {
                    out.println("Conexão encerrada.");
                    break;
                }
                
                processCommand(command, out);
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }

    private void processCommand(String command, PrintWriter out) {
        String[] parts = command.split(" ", 2);
        
        if (parts.length < 2 || !parts[0].equalsIgnoreCase("GET")) {
            out.println("ERRO: Comando invalido. Use: GET <nome_arquivo>");
            return;
        }
        
        String filename = parts[1];
        File file = new File(FILES_DIRECTORY + filename);
        
        if (!file.exists() || !file.isFile()) {
            out.println("ARQUIVO NAO ENCONTRADO");
            System.out.println("Arquivo nao encontrado: " + filename);
            return;
        }
        
        try {
            out.println("INICIO_ARQUIVO");
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
                lineCount++;
            }
            
            out.println("FIM_ARQUIVO");
            fileReader.close();
            
            System.out.println("Arquivo enviado: " + filename + " (" + lineCount + " linhas)");
        } catch (IOException e) {
            out.println("ERRO: Falha ao ler arquivo");
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
