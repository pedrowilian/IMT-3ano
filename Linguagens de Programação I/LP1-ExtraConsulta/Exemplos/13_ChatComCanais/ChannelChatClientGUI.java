import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChannelChatClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField, nicknameField;
    private JButton sendButton, connectButton;
    private JComboBox<String> channelCombo;
    private JLabel currentChannelLabel;
    private Socket socket;
    private PrintWriter out;
    private String myNickname;
    private String currentChannel = "geral";
    
    public ChannelChatClientGUI() {
        setTitle("Chat com Canais");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        
        JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        connectionPanel.add(new JLabel("Nickname:"));
        nicknameField = new JTextField(15);
        connectionPanel.add(nicknameField);
        connectButton = new JButton("Conectar");
        connectionPanel.add(connectButton);
        
        JPanel channelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        channelPanel.add(new JLabel("Canal:"));
        channelCombo = new JComboBox<>(new String[]{"geral", "games", "tech", "+ novo"});
        channelCombo.setEnabled(false);
        channelPanel.add(channelCombo);
        currentChannelLabel = new JLabel("Canal atual: #geral");
        currentChannelLabel.setFont(new Font("Arial", Font.BOLD, 13));
        channelPanel.add(currentChannelLabel);
        
        topPanel.add(connectionPanel, BorderLayout.NORTH);
        topPanel.add(channelPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        
        // Area de chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 13));
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chat"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        
        inputField = new JTextField();
        inputField.setEnabled(false);
        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);
        
        JLabel hintLabel = new JLabel("💡 Comandos: /join <canal> | /channels | /users");
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        hintLabel.setForeground(Color.GRAY);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        bottomPanel.add(hintLabel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
        channelCombo.addActionListener(e -> changeChannel());
        
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
            socket = new Socket("localhost", 5012);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            myNickname = nickname;
            setTitle("Chat - " + myNickname);
            
            new Thread(() -> {
                try {
                    String message;
                    boolean nameSet = false;
                    
                    while ((message = in.readLine()) != null) {
                        final String msg = message;
                        
                        if (!nameSet && msg.contains("Digite seu nickname:")) {
                            out.println(myNickname);
                            nameSet = true;
                            continue;
                        }
                        
                        SwingUtilities.invokeLater(() -> {
                            if (msg.contains("Você está no canal:") || msg.contains("Você entrou no canal")) {
                                String[] parts = msg.split("#");
                                if (parts.length > 1) {
                                    currentChannel = parts[1].trim();
                                    currentChannelLabel.setText("Canal atual: #" + currentChannel);
                                }
                            }
                            chatArea.append(msg + "\n");
                            chatArea.setCaretPosition(chatArea.getDocument().getLength());
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> chatArea.append("\n=== Desconectado ===\n"));
                }
            }).start();
            
            connectButton.setEnabled(false);
            nicknameField.setEnabled(false);
            inputField.setEnabled(true);
            sendButton.setEnabled(true);
            channelCombo.setEnabled(true);
            inputField.requestFocus();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar: " + e.getMessage());
        }
    }
    
    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            inputField.setText("");
        }
    }
    
    private void changeChannel() {
        String selected = (String) channelCombo.getSelectedItem();
        if (selected == null || out == null) return;
        
        if (selected.equals("+ novo")) {
            String newChannel = JOptionPane.showInputDialog(this, "Nome do novo canal:");
            if (newChannel != null && !newChannel.trim().isEmpty()) {
                out.println("/join " + newChannel.trim());
            }
            channelCombo.setSelectedIndex(0);
        } else {
            out.println("/join " + selected);
        }
    }
    
    private void disconnect() {
        try {
            if (out != null) out.println("/quit");
            if (socket != null) socket.close();
        } catch (IOException e) {}
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChannelChatClientGUI().setVisible(true));
    }
}
