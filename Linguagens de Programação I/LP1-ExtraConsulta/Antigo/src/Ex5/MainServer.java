package Ex5;

import java.io.IOException;
import java.net.InetSocketAddress; // Import necessário
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;

public class MainServer {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    public void start() throws IOException {
        // --- CORREÇÃO AQUI ---
        // Em vez de new ServerSocket(PORT), fazemos em passos para configurar o ReuseAddress
        serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true); // Permite reiniciar o servidor imediatamente
        serverSocket.bind(new InetSocketAddress(PORT));
        // ---------------------
        
        System.out.println("Servidor iniciado na porta " + PORT);
        clientConnectionLoop();
    }

    public void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando conexões de clientes...");
        while (true){
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            if (clients.size() >= 2)
            {
                clientSocket.sendMsg("--MAXIMUMLIMIT--");
                clientSocket.close();
                System.out.println("Limite de clientes atingido");
                // break; // Remover este break se quiser que o servidor continue rodando rejeitando extras
                // Se deixar o break, o servidor para de aceitar qualquer um após o limite.
            }
            else {
                clients.add(clientSocket);
                new Thread(() -> clientMessageLoop(clientSocket)).start();
            }
        }
    }

    public void clientMessageLoop(SocketCliente clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if("sair".equalsIgnoreCase(msg)) {
                    System.out.println("Conexão com " + clientSocket.getRemoteSocketAddress() + " encerrada!");
                    clients.remove(clientSocket);
                    // Não remova o socket da lista aqui e depois no finally, ou trate com cuidado
                    // O ideal é o break levar ao finally
                    break; 
                } else if ("--FINALIZAR--".equalsIgnoreCase(msg)) {
                    System.out.println("Finalizando servidor...");
                    System.exit(0);
                } else {
                    System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
                    sendMsgToAll(clientSocket, msg);
                }
            }
        }
        finally {
            clientSocket.close();
            // Garante que remove da lista se caiu por erro ou break
            if(clients.contains(clientSocket)){
                clients.remove(clientSocket);
            }
            System.out.println("Clientes conectados: " + clients.size());
        }
    }

    private void sendMsgToAll(SocketCliente sender, String msg){
        Iterator<SocketCliente> iterator = clients.iterator();
        while(iterator.hasNext()){
            SocketCliente clientSocket = iterator.next();
            if(!sender.equals(clientSocket)){
                if(!clientSocket.sendMsg("Cliente " + sender.getRemoteSocketAddress() + " disse: " + msg)){
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("---- CONSOLE DO SERVIDOR ----");
        try{
            MainServer server = new MainServer();
            server.start();
        }
        catch(IOException e){
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
        System.out.println("---- FIM DO SERVIDOR ----");
    }
}