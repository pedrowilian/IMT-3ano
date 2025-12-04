import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class PingClientGUI extends JFrame {
    private JTextArea logArea;
    private JTextField nameField;
    private JButton connectButton, pingButton, timeButton;
    private Socket socket;
    private PrintWriter out;
    
    public PingClientGUI() {
        setTitle("Cliente Ping Sincronizado");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nome:"));
        nameField = new JTextField(10);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        pingButton = new JButton("Enviar PING");
        pingButton.setEnabled(false);
        bottomPanel.add(pingButton);
        timeButton = new JButton("Solicitar Hora do Servidor");
        timeButton.setEnabled(false);
        bottomPanel.add(timeButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        pingButton.addActionListener(e -> sendPing());
        timeButton.addActionListener(e -> {if(out!=null) out.println("TIME");});
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5018);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        if (!nameSet && msg.contains("Nome:")) {
                            out.println(nameField.getText());
                            nameSet = true;
                            continue;
                        }
                        final String m = msg;
                        SwingUtilities.invokeLater(() -> logArea.append(m + "\n"));
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            pingButton.setEnabled(true);
            timeButton.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void sendPing() {
        if (out != null) {
            long timestamp = System.currentTimeMillis();
            out.println("PING " + timestamp);
            logArea.append("Enviado PING em: " + timestamp + "\n");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PingClientGUI().setVisible(true));
    }
}
