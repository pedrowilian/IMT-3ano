import java.io.*;
import java.net.*;
import java.util.*;

public class AgendaServer {
    private static final int PORT = 5009;

    public static void main(String[] args) {
        System.out.println("Servidor de Agenda iniciado na porta " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                
                new Thread(new AgendaHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class AgendaHandler implements Runnable {
    private Socket socket;
    private List<String> appointments;

    public AgendaHandler(Socket socket) {
        this.socket = socket;
        this.appointments = new ArrayList<>();
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("Bem-vindo ao Servidor de Agenda");
            out.println("Comandos: ADD <descrição> <horário>, LIST, DEL <número>, SAIR");
            
            String command;
            while ((command = in.readLine()) != null) {
                if (command.equalsIgnoreCase("SAIR")) {
                    out.println("Conexão encerrada.");
                    break;
                }
                
                String response = processCommand(command);
                out.println(response);
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
        String[] parts = command.split(" ", 2);
        String action = parts[0].toUpperCase();
        
        switch (action) {
            case "ADD":
                return addAppointment(parts);
                
            case "LIST":
                return listAppointments();
                
            case "DEL":
                return deleteAppointment(parts);
                
            default:
                return "ERRO: Comando inválido. Use: ADD, LIST, DEL ou SAIR";
        }
    }

    private String addAppointment(String[] parts) {
        if (parts.length < 2) {
            return "ERRO: Formato incorreto. Use: ADD <descrição> <horário>";
        }
        
        appointments.add(parts[1]);
        return "Compromisso adicionado: " + parts[1];
    }

    private String listAppointments() {
        if (appointments.isEmpty()) {
            return "Nenhum compromisso agendado.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== Compromissos ===\n");
        for (int i = 0; i < appointments.size(); i++) {
            sb.append((i + 1)).append(". ").append(appointments.get(i)).append("\n");
        }
        sb.append("====================");
        return sb.toString();
    }

    private String deleteAppointment(String[] parts) {
        if (parts.length < 2) {
            return "ERRO: Formato incorreto. Use: DEL <número>";
        }
        
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            
            if (index < 0 || index >= appointments.size()) {
                return "ERRO: Número de compromisso inválido.";
            }
            
            String removed = appointments.remove(index);
            return "Compromisso removido: " + removed;
        } catch (NumberFormatException e) {
            return "ERRO: Número inválido.";
        }
    }
}
