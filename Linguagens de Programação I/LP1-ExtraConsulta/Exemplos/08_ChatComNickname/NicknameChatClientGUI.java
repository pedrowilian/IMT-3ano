import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class NicknameChatClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField nicknameField;
    private JButton sendButton;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean nicknameSet = false;
    
    public NicknameChatClientGUI() {
        setTitle("Chat com Nickname");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Area de chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 13));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel superior - conexão
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Nickname:"));
        nicknameField = new JTextField(15);
        topPanel.add(nicknameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        // Panel inferior - mensagens
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        inputField = new JTextField();
        inputField.setEnabled(false);
        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);
        
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Ações
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
        String nickname = nicknameField.getText().trim();
        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nickname!");
            return;
        }
        
        try {
            socket = new Socket("localhost", 5007);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            chatArea.append("=== Conectando ao servidor... ===\n\n");
            connectButton.setEnabled(false);
            nicknameField.setEnabled(false);
            
            // Thread para receber mensagens
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        final String message = msg;
                        
                        SwingUtilities.invokeLater(() -> {
                            chatArea.append(message + "\n");
                            
                            // Quando o servidor pede o nome
                            if (message.contains("Digite seu nome:") && !nicknameSet) {
                                out.println(nicknameField.getText().trim());
                                nicknameSet = true;
                                inputField.setEnabled(true);
                                sendButton.setEnabled(true);
                                inputField.requestFocus();
                                setTitle("Chat - " + nicknameField.getText().trim());
                            }
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> chatArea.append("\n=== Desconectado ===\n"));
                }
            }).start();
            
        } catch (IOException e) {
            chatArea.append("Erro ao conectar: " + e.getMessage() + "\n");
            connectButton.setEnabled(true);
            nicknameField.setEnabled(true);
        }
    }
    
    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            inputField.setText("");
        }
    }
    
    private void disconnect() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NicknameChatClientGUI().setVisible(true);
        });
    }
}
