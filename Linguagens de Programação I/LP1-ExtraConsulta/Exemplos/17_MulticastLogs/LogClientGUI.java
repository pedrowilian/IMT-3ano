import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class LogClientGUI extends JFrame {
    private JTextArea logArea;
    private JTextField nameField, logField;
    private JButton connectButton, sendButton;
    private Socket socket;
    private PrintWriter out;
    
    public LogClientGUI() {
        setTitle("Sistema de Logs");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Agente:"));
        nameField = new JTextField(10);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        add(new JScrollPane(logArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel hint = new JLabel("Formato: LOG agente42 CPU=90% MEM=75%");
        hint.setFont(new Font("SansSerif", Font.ITALIC, 11));
        bottomPanel.add(hint, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        logField = new JTextField(30);
        logField.setEnabled(false);
        inputPanel.add(logField);
        sendButton = new JButton("Enviar Log");
        sendButton.setEnabled(false);
        inputPanel.add(sendButton);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        sendButton.addActionListener(e -> sendLog());
        logField.addActionListener(e -> sendLog());
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5016);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        if (!nameSet && msg.contains("agente:")) {
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
            logField.setEnabled(true);
            sendButton.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void sendLog() {
        String log = logField.getText().trim();
        if (!log.isEmpty()) {
            if (!log.toUpperCase().startsWith("LOG ")) {
                log = "LOG " + log;
            }
            out.println(log);
            logField.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogClientGUI().setVisible(true));
    }
}
