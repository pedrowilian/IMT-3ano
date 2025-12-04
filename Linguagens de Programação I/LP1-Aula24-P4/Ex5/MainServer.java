import java.io.IOException;
import java.net.InetSocketAddress; 
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;

public class MainServer {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    public void start() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true); 
        serverSocket.bind(new InetSocketAddress(PORT));
        
        System.out.println("Servidor iniciado na porta " + PORT);
        clientConnectionLoop();
    }

    public void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando conexões de até 2 clientes...");
        while (true){
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            if (clients.size() >= 2)
            {
                clientSocket.sendMsg("--MAXIMUMLIMIT--");
                clientSocket.close();
                System.out.println("Limite de clientes atingido");
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
                    break; 
                } else if ("--FINALIZAR--".equalsIgnoreCase(msg)) {
                    System.out.println("Finalizando servidor...");
                    System.exit(0);
                } else {
                    //System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
                    sendMsgToAll(clientSocket, msg);
                }
            }
        }
        finally {
            clientSocket.close();
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