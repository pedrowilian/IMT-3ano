import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

public class ClienteBatepapo implements Runnable{
    private SocketCliente clientSocket;
    private Scanner scanner;
    private JTextArea textArea;
    private InterfaceChat frame;

    public ClienteBatepapo(){
        scanner = new Scanner(System.in);
    }

    public void start(JTextArea textArea, InterfaceChat frame) throws IOException{
        this.frame = frame;
        this.textArea = textArea;
        try{
            clientSocket = new SocketCliente(new Socket(MainServer.ADDRESS, MainServer.PORT));
            new Thread(this).start();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao iniciar cliente: " + e.getMessage());
            System.exit(0);

        }
    }

    @Override
    public void run(){
        String msg;
        while((msg = clientSocket.getMessage()) != null){
            if (msg.equals("--MAXIMUMLIMIT--"))
            {
                JOptionPane.showMessageDialog(null, "Limite máximo atingido, fechando...");
                System.exit(0);

            }
            frame.adicionarMensagem(msg);
        }
    }

    public void sendMsg(String msg) {
        clientSocket.sendMsg(msg);
    }

    public void close(){
        clientSocket.close();
    }
}
