import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketCliente{
    private final Socket socket;
    private final BufferedReader entrada;
    private final PrintWriter saida;

    public SocketCliente(final Socket socket) throws IOException{
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close(){
        try{
            entrada.close();
            saida.close();
            socket.close();
        }
        catch(IOException e){
            System.out.println("Erro ao fechar socket: " + e.getMessage());
        }
    }

    public String getMessage(){
        try {
            return entrada.readLine();
        } catch (IOException e) {
            System.out.println("Finalizando cliente " + socket.getRemoteSocketAddress() + "...");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean sendMsg(String msg){
        saida.println(msg);
        return !saida.checkError();
    }
    
}
