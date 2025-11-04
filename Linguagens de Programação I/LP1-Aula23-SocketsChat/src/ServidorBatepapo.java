import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServidorBatepapo {
    public static final String ADDRESS =  "127.0.0.1";      //Endereço IP local do servidor
    public static final int PORT = 4000;                    //ou 3334
    private ServerSocket serverSocket;
    private final List<SocketCliente> clientes = new LinkedList<>();
    
    public void start() throws IOException{
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: "+PORT);
        clientConnectionLoop();
    }
    private void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando conexão de um cliente!");
        while(true){
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            clientes.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }
    }
    private void clientMessageLoop(SocketCliente clientSocket){
        String msg;
        try {
            while((msg=clientSocket.getMessage()) != null){
                if("sair".equalsIgnoreCase(msg))    return;
                System.out.printf("<- Cliente %s: %s\n",clientSocket.getRemoteSocketAddress(), msg);
                sendMsgToAll(clientSocket, msg);
            }
        } finally{
            clientSocket.close();
        }
    }
    private void sendMsgToAll(SocketCliente sender, String msg){
        Iterator<SocketCliente> iterator = clientes.iterator();
        while(iterator.hasNext()){
            SocketCliente clientSocket = iterator.next();
            if(!sender.equals(clientSocket)){
                if(!clientSocket.sendMsg("Cliente "+sender.getRemoteSocketAddress()+": "+msg)){
                    iterator.remove();
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            ServidorBatepapo server = new ServidorBatepapo();
            server.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o servidor: "+ex.getMessage());
        }
        System.out.println("Servidor finalizado!");
    }
}
