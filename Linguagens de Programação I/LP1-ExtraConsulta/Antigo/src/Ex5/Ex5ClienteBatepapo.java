package Ex5;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Ex5ClienteBatepapo implements Runnable{
    private SocketCliente clientSocket;
    private Scanner scanner;
    private JTextArea textArea;
    private ChatScreen frame;

    public Ex5ClienteBatepapo(){
        scanner = new Scanner(System.in);
    }

    public void start(JTextArea textArea, ChatScreen frame) throws IOException{
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
            frame.addServerMessage(msg);
        }
    }

    public void sendMsg(String msg) {
        clientSocket.sendMsg(msg);
    }

    public void close(){
        clientSocket.close();
    }
}
