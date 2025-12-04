import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockClientGUI extends JFrame {
    private JLabel timeLabel;
    private JTextArea logArea;
    private JButton connectButton;
    private JButton disconnectButton;
    private Socket socket;
    private BufferedReader in;
    
    public ClockClientGUI() {
        setTitle("Clock Client - Tempo Real");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Display do relógio
        JPanel clockPanel = new JPanel();
        clockPanel.setBackground(new Color(40, 40, 40));
        clockPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        timeLabel = new JLabel("--:--:--");
        timeLabel.setFont(new Font("Digital-7", Font.BOLD, 72));
        timeLabel.setForeground(new Color(0, 255, 0));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        clockPanel.add(timeLabel);
        add(clockPanel, BorderLayout.NORTH);
        
        // Area de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        connectButton = new JButton("Conectar ao Servidor");
        disconnectButton = new JButton("Desconectar");
        disconnectButton.setEnabled(false);
        buttonPanel.add(connectButton);
        buttonPanel.add(disconnectButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        disconnectButton.addActionListener(e -> disconnect());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5008);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            logArea.append("=== Conectado ao Clock Server ===\n");
            logArea.append("Recebendo hora do servidor a cada segundo...\n\n");
            
            connectButton.setEnabled(false);
            disconnectButton.setEnabled(true);
            
            // Thread para receber horários
            new Thread(() -> {
                try {
                    String time;
                    while ((time = in.readLine()) != null) {
                        final String currentTime = time;
                        SwingUtilities.invokeLater(() -> {
                            timeLabel.setText(currentTime);
                            logArea.append("Hora recebida: " + currentTime + "\n");
                            
                            // Auto-scroll
                            logArea.setCaretPosition(logArea.getDocument().getLength());
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("\n=== Conexão encerrada ===\n");
                        timeLabel.setText("--:--:--");
                        connectButton.setEnabled(true);
                        disconnectButton.setEnabled(false);
                    });
                }
            }).start();
            
        } catch (IOException e) {
            logArea.append("Erro ao conectar: " + e.getMessage() + "\n");
            JOptionPane.showMessageDialog(this, 
                "Não foi possível conectar ao servidor.\nVerifique se o servidor está rodando.", 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void disconnect() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            logArea.append("\n=== Desconectado ===\n");
            timeLabel.setText("--:--:--");
            connectButton.setEnabled(true);
            disconnectButton.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClockClientGUI().setVisible(true);
        });
    }
}
