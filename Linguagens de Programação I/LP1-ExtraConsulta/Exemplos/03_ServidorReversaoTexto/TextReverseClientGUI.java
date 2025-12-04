import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class TextReverseClientGUI extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public TextReverseClientGUI() {
        setTitle("Text Reverse Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        inputField = new JTextField();
        inputField.setEnabled(false);
        sendButton = new JButton("Reverter");
        sendButton.setEnabled(false);
        connectButton = new JButton("Conectar");
        
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(new JLabel("Texto: "), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(connectButton, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connectToServer());
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5002);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            textArea.append("Conectado! Digite 'FIM' para sair.\n\n");
            connectButton.setEnabled(false);
            inputField.setEnabled(true);
            sendButton.setEnabled(true);
            inputField.requestFocus();
            
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        final String message = msg;
                        SwingUtilities.invokeLater(() -> textArea.append("Revertido: " + message + "\n\n"));
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> textArea.append("Desconectado.\n"));
                }
            }).start();
            
        } catch (IOException e) {
            textArea.append("Erro ao conectar: " + e.getMessage() + "\n");
        }
    }
    
    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            textArea.append("Original: " + msg + "\n");
            out.println(msg);
            inputField.setText("");
            
            if (msg.equalsIgnoreCase("FIM")) {
                disconnect();
            }
        }
    }
    
    private void disconnect() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
            inputField.setEnabled(false);
            sendButton.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextReverseClientGUI().setVisible(true);
        });
    }
}
