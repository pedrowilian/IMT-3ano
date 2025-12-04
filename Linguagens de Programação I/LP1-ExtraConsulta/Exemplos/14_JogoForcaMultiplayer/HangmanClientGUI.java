import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class HangmanClientGUI extends JFrame {
    private JTextArea gameArea;
    private JTextField nameField, letterField;
    private JButton connectButton, guessButton;
    private JLabel stateLabel, errorLabel;
    private Socket socket;
    private PrintWriter out;
    
    public HangmanClientGUI() {
        setTitle("Jogo da Forca Multiplayer");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Nome:"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        connectButton = new JButton("Conectar");
        namePanel.add(connectButton);
        
        stateLabel = new JLabel("Palavra: ___________");
        stateLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        errorLabel = new JLabel("Erradas: | Erros: 0/6");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        
        topPanel.add(namePanel);
        topPanel.add(stateLabel);
        topPanel.add(errorLabel);
        add(topPanel, BorderLayout.NORTH);
        
        gameArea = new JTextArea();
        gameArea.setEditable(false);
        gameArea.setFont(new Font("Arial", Font.PLAIN, 12));
        add(new JScrollPane(gameArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("Letra:"));
        letterField = new JTextField(3);
        letterField.setEnabled(false);
        letterField.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(letterField);
        guessButton = new JButton("Tentar");
        guessButton.setEnabled(false);
        bottomPanel.add(guessButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        guessButton.addActionListener(e -> guess());
        letterField.addActionListener(e -> guess());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connect() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite seu nome!");
            return;
        }
        
        try {
            socket = new Socket("localhost", 5013);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        final String message = msg;
                        if (!nameSet && msg.contains("Digite seu nome:")) {
                            out.println(name);
                            nameSet = true;
                            continue;
                        }
                        SwingUtilities.invokeLater(() -> {
                            processMessage(message);
                            gameArea.append(message + "\n");
                            gameArea.setCaretPosition(gameArea.getDocument().getLength());
                        });
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            nameField.setEnabled(false);
            letterField.setEnabled(true);
            guessButton.setEnabled(true);
            letterField.requestFocus();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar!");
        }
    }
    
    private void processMessage(String msg) {
        if (msg.startsWith("ATUAL:")) {
            stateLabel.setText("Palavra: " + msg.substring(7).trim());
        } else if (msg.startsWith("ERRADAS:")) {
            String errors = msg.substring(9).trim();
            errorLabel.setText("Erradas: " + errors);
        } else if (msg.contains("VENCEDOR:") || msg.contains("GAME_OVER:")) {
            guessButton.setEnabled(false);
            letterField.setEnabled(false);
        }
    }
    
    private void guess() {
        String letter = letterField.getText().trim().toUpperCase();
        if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
            out.println("LETRA " + letter);
            letterField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Digite apenas UMA letra!");
        }
    }
    
    private void disconnect() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {}
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanClientGUI().setVisible(true));
    }
}
