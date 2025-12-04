import java.io.*;
import java.net.*;

public class MathServer {
    private static final int PORT = 5004;

    public static void main(String[] args) {
        System.out.println("Servidor de Operações Matemáticas iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new MathHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class MathHandler implements Runnable {
    private Socket socket;

    public MathHandler(Socket socket) {
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
                
                String result = processCommand(command);
                out.println(result);
                System.out.println("Comando: " + command + " | Resultado: " + result);
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

    private String processCommand(String command) {
        try {
            String[] parts = command.split(" ");
            
            if (parts.length != 3) {
                return "ERRO";
            }
            
            String operation = parts[0].toUpperCase();
            double num1 = Double.parseDouble(parts[1]);
            double num2 = Double.parseDouble(parts[2]);
            
            double result;
            switch (operation) {
                case "SOMA":
                    result = num1 + num2;
                    break;
                case "SUB":
                    result = num1 - num2;
                    break;
                case "MULT":
                    result = num1 * num2;
                    break;
                case "DIV":
                    if (num2 == 0) {
                        return "ERRO: Divisão por zero";
                    }
                    result = num1 / num2;
                    break;
                default:
                    return "ERRO";
            }
            
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "ERRO";
        }
    }
}
