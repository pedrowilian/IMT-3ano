import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MuteClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField nameField, messageField, muteField;
    private JButton connectButton, sendButton, muteButton, usersButton;
    private Socket socket;
    private PrintWriter out;
    private Set<String> mutedUsers = new HashSet<>();
    
    public MuteClientGUI() {
        setTitle("Chat com Silenciamento");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nickname:"));
        nameField = new JTextField(10);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        JPanel msgPanel = new JPanel(new FlowLayout());
        messageField = new JTextField(30);
        messageField.setEnabled(false);
        msgPanel.add(messageField);
        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);
        msgPanel.add(sendButton);
        usersButton = new JButton("/users");
        usersButton.setEnabled(false);
        msgPanel.add(usersButton);
        bottomPanel.add(msgPanel, BorderLayout.NORTH);
        
        JPanel mutePanel = new JPanel(new FlowLayout());
        mutePanel.add(new JLabel("Silenciar:"));
        muteField = new JTextField(10);
        muteField.setEnabled(false);
        mutePanel.add(muteField);
        muteButton = new JButton("Mute/Unmute");
        muteButton.setEnabled(false);
        mutePanel.add(muteButton);
        bottomPanel.add(mutePanel, BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        muteButton.addActionListener(e -> toggleMute());
        usersButton.addActionListener(e -> {if(out!=null) out.println("/users");});
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5019);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        if (!nameSet && msg.contains("nickname:")) {
                            out.println(nameField.getText());
                            nameSet = true;
                            continue;
                        }
                        
                        final String message = msg;
                        SwingUtilities.invokeLater(() -> {
                            if (message.startsWith("FROM:")) {
                                String[] parts = message.split(":", 3);
                                if (parts.length >= 3) {
                                    String sender = parts[1];
                                    String content = parts[2];
                                    if (!mutedUsers.contains(sender)) {
                                        chatArea.append(content + "\n");
                                    }
                                }
                            } else {
                                chatArea.append(message + "\n");
                            }
                        });
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            messageField.setEnabled(true);
            sendButton.setEnabled(true);
            muteField.setEnabled(true);
            muteButton.setEnabled(true);
            usersButton.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void sendMessage() {
        String msg = messageField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            messageField.setText("");
        }
    }
    
    private void toggleMute() {
        String user = muteField.getText().trim();
        if (!user.isEmpty()) {
            if (mutedUsers.contains(user)) {
                mutedUsers.remove(user);
                chatArea.append(">>> " + user + " desmutado <<<\n");
            } else {
                mutedUsers.add(user);
                chatArea.append(">>> " + user + " mutado <<<\n");
            }
            muteField.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MuteClientGUI().setVisible(true));
    }
}
