import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;

public class PrivateMessageClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField nicknameField;
    private JButton sendButton;
    private JButton connectButton;
    private JButton usersButton;
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String myNickname;
    
    public PrivateMessageClientGUI() {
        setTitle("Chat com Mensagens Privadas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Conexão
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        topPanel.add(new JLabel("Nickname:"));
        nicknameField = new JTextField(15);
        topPanel.add(nicknameField);
        connectButton = new JButton("Conectar");
        connectButton.setFont(new Font("Arial", Font.BOLD, 12));
        topPanel.add(connectButton);
        usersButton = new JButton("Atualizar Usuários");
        usersButton.setEnabled(false);
        topPanel.add(usersButton);
        add(topPanel, BorderLayout.NORTH);
        
        // Panel central - Chat e lista de usuários
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        
        // Area de chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 13));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBorder(BorderFactory.createTitledBorder("Chat"));
        
        // Lista de usuários
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setFont(new Font("Arial", Font.PLAIN, 12));
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane userScrollPane = new JScrollPane(userList);
        userScrollPane.setBorder(BorderFactory.createTitledBorder("Usuários Online"));
        userScrollPane.setPreferredSize(new Dimension(180, 0));
        
        centerPanel.add(chatScrollPane, BorderLayout.CENTER);
        centerPanel.add(userScrollPane, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior - Envio de mensagens
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputField = new JTextField();
        inputField.setEnabled(false);
        inputField.setFont(new Font("Arial", Font.PLAIN, 13));
        
        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);
        sendButton.setPreferredSize(new Dimension(80, 30));
        
        JButton privateButton = new JButton("@Privado");
        privateButton.setEnabled(false);
        privateButton.setPreferredSize(new Dimension(90, 30));
        privateButton.addActionListener(e -> sendPrivateToSelected());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.add(privateButton);
        buttonPanel.add(sendButton);
        
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Dica de uso
        JLabel hintLabel = new JLabel("💡 Dica: @usuario mensagem (privado) | mensagem normal (todos) | /users (listar)");
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        hintLabel.setForeground(Color.GRAY);
        
        bottomPanel.add(hintLabel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
        usersButton.addActionListener(e -> requestUserList());
        
        // Duplo clique na lista para enviar privado
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    sendPrivateToSelected();
                }
            }
        });
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
        
        // Tornar botões habilitados quando conectado
        connectButton.addActionListener(e -> {
            privateButton.setEnabled(true);
        });
    }
    
    private void connectToServer() {
        String nickname = nicknameField.getText().trim();
        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nickname!");
            return;
        }
        
        try {
            socket = new Socket("localhost", 5010);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            myNickname = nickname;
            
            // Thread para receber mensagens
            new Thread(() -> {
                try {
                    String message;
                    boolean nicknameSet = false;
                    
                    while ((message = in.readLine()) != null) {
                        final String msg = message;
                        
                        // Enviar nickname quando servidor solicitar
                        if (!nicknameSet && msg.contains("Digite seu nickname:")) {
                            out.println(myNickname);
                            nicknameSet = true;
                            SwingUtilities.invokeLater(() -> {
                                chatArea.append("=== Conectando como " + myNickname + " ===\n");
                            });
                            continue;
                        }
                        
                        // Verificar se nickname foi aceito
                        if (msg.contains("Bem-vindo, " + myNickname)) {
                            SwingUtilities.invokeLater(() -> {
                                setTitle("Chat - " + myNickname);
                                connectButton.setEnabled(false);
                                nicknameField.setEnabled(false);
                                inputField.setEnabled(true);
                                sendButton.setEnabled(true);
                                usersButton.setEnabled(true);
                                inputField.requestFocus();
                            });
                        }
                        
                        // Verificar se nickname foi rejeitado
                        if (msg.contains("já está em uso") || msg.contains("inválido")) {
                            SwingUtilities.invokeLater(() -> {
                                chatArea.append(msg + "\n");
                                JOptionPane.showMessageDialog(PrivateMessageClientGUI.this, 
                                    msg.replace("SERVER: ", ""), 
                                    "Erro", 
                                    JOptionPane.ERROR_MESSAGE);
                                resetConnection();
                            });
                            return;
                        }
                        
                        SwingUtilities.invokeLater(() -> {
                            processMessage(msg);
                            chatArea.append(msg + "\n");
                            chatArea.setCaretPosition(chatArea.getDocument().getLength());
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> {
                        chatArea.append("\n=== Desconectado do servidor ===\n");
                        resetConnection();
                    });
                }
            }).start();
            
        } catch (IOException e) {
            chatArea.append("Erro ao conectar: " + e.getMessage() + "\n");
            JOptionPane.showMessageDialog(this, 
                "Não foi possível conectar ao servidor.\nVerifique se o servidor está rodando.",
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void processMessage(String message) {
        // Extrair usuários da lista quando servidor enviar
        if (message.contains("Usuários Online")) {
            userListModel.clear();
        } else if (message.startsWith("SERVER:   - ")) {
            String user = message.substring(12).trim();
            if (user.endsWith(" (você)")) {
                user = user.substring(0, user.length() - 7);
            }
            if (!userListModel.contains(user)) {
                userListModel.addElement(user);
            }
        } else if (message.contains("entrou no chat") || message.contains("saiu do chat")) {
            // Atualizar lista quando alguém entra ou sai
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(100);
                    requestUserList();
                } catch (InterruptedException e) {}
            });
        }
    }
    
    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            inputField.setText("");
        }
    }
    
    private void sendPrivateToSelected() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário da lista!");
            return;
        }
        
        if (selectedUser.equals(myNickname)) {
            JOptionPane.showMessageDialog(this, "Você não pode enviar mensagem para si mesmo!");
            return;
        }
        
        String message = JOptionPane.showInputDialog(this, 
            "Mensagem privada para " + selectedUser + ":", 
            "Mensagem Privada", 
            JOptionPane.PLAIN_MESSAGE);
        
        if (message != null && !message.trim().isEmpty()) {
            out.println("@" + selectedUser + " " + message);
        }
    }
    
    private void requestUserList() {
        if (out != null) {
            out.println("/users");
        }
    }
    
    private void resetConnection() {
        connectButton.setEnabled(true);
        nicknameField.setEnabled(true);
        inputField.setEnabled(false);
        sendButton.setEnabled(false);
        usersButton.setEnabled(false);
        userListModel.clear();
    }
    
    private void disconnect() {
        try {
            if (out != null) out.println("/quit");
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrivateMessageClientGUI gui = new PrivateMessageClientGUI();
            gui.setVisible(true);
        });
    }
}
