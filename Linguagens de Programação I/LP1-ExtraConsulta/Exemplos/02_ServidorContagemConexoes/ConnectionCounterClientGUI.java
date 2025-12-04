import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ConnectionCounterClientGUI extends JFrame {
    private JTextArea textArea;
    private JButton connectButton;
    
    public ConnectionCounterClientGUI() {
        setTitle("Connection Counter Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        connectButton = new JButton("Conectar ao Servidor");
        connectButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(connectButton, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connectToServer());
    }
    
    private void connectToServer() {
        connectButton.setEnabled(false);
        textArea.append("Conectando ao servidor...\n");
        
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", 5001);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                
                String message = in.readLine();
                SwingUtilities.invokeLater(() -> {
                    textArea.append("\n" + message + "\n\n");
                    textArea.append("Conexão encerrada.\n");
                    connectButton.setEnabled(true);
                });
                
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    textArea.append("Erro: " + e.getMessage() + "\n");
                    connectButton.setEnabled(true);
                });
            }
        }).start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConnectionCounterClientGUI().setVisible(true);
        });
    }
}
