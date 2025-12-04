import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class AutoPingClientGUI extends JFrame {
    private JTextArea logArea;
    private JButton startButton;
    private JButton stopButton;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean running = false;
    
    public AutoPingClientGUI() {
        setTitle("Auto Ping Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Area de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Status: Desconectado");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(statusLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // Panel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Iniciar Auto-Ping (10 mensagens)");
        stopButton = new JButton("Parar");
        stopButton.setEnabled(false);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        
        progressBar = new JProgressBar(0, 10);
        progressBar.setStringPainted(true);
        
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Ações
        startButton.addActionListener(e -> startAutoPing());
        stopButton.addActionListener(e -> stopAutoPing());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                stopAutoPing();
            }
        });
    }
    
    private void startAutoPing() {
        logArea.setText("");
        progressBar.setValue(0);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        running = true;
        
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5006);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Status: Conectado");
                    logArea.append("=== Conectado ao servidor ===\n");
                    logArea.append("Enviando 10 pings com intervalo de 2 segundos...\n\n");
                });
                
                // Thread para receber respostas
                new Thread(() -> {
                    try {
                        String msg;
                        while (running && (msg = in.readLine()) != null) {
                            final String response = msg;
                            SwingUtilities.invokeLater(() -> 
                                logArea.append(" Servidor: " + response + "\n"));
                        }
                    } catch (IOException e) {
                        // Conexão fechada
                    }
                }).start();
                
                // Enviar 10 pings
                for (int i = 1; i <= 10 && running; i++) {
                    final int count = i;
                    SwingUtilities.invokeLater(() -> {
                        logArea.append(String.format("[%d/10] → Enviando: ping\n", count));
                        progressBar.setValue(count);
                    });
                    
                    out.println("ping");
                    Thread.sleep(2000);
                }
                
                if (running) {
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("\n=== 10 mensagens enviadas! ===\n");
                        statusLabel.setText("Status: Concluído");
                    });
                }
                
                disconnect();
                
            } catch (IOException | InterruptedException e) {
                SwingUtilities.invokeLater(() -> {
                    logArea.append("\nErro: " + e.getMessage() + "\n");
                    statusLabel.setText("Status: Erro");
                });
            } finally {
                SwingUtilities.invokeLater(() -> {
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                });
            }
        }).start();
    }
    
    private void stopAutoPing() {
        running = false;
        disconnect();
        statusLabel.setText("Status: Parado");
        logArea.append("\n=== Parado pelo usuário ===\n");
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
            new AutoPingClientGUI().setVisible(true);
        });
    }
}
