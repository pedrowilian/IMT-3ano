import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;

public class AuctionClientGUI extends JFrame {
    private JTextArea auctionArea;
    private JTextField nameField, bidField;
    private JButton connectButton, bidButton, statusButton;
    private JLabel currentBidLabel, winnerLabel;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String myName;
    
    public AuctionClientGUI() {
        setTitle("Leilão em Tempo Real");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Seu nome:"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        connectButton = new JButton("Conectar");
        namePanel.add(connectButton);
        
        currentBidLabel = new JLabel("Lance Atual: R$ 0.00");
        currentBidLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentBidLabel.setForeground(new Color(0, 128, 0));
        
        winnerLabel = new JLabel("Vencedor: Ninguém");
        winnerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        topPanel.add(namePanel);
        topPanel.add(currentBidLabel);
        topPanel.add(winnerLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // Area central
        auctionArea = new JTextArea();
        auctionArea.setEditable(false);
        auctionArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(auctionArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Historico do Leilao"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JPanel bidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bidPanel.add(new JLabel("Seu Lance: R$"));
        bidField = new JTextField(10);
        bidField.setEnabled(false);
        bidPanel.add(bidField);
        bidButton = new JButton("Dar Lance");
        bidButton.setEnabled(false);
        bidButton.setBackground(new Color(255, 140, 0));
        bidButton.setForeground(Color.WHITE);
        bidButton.setFont(new Font("Arial", Font.BOLD, 14));
        bidPanel.add(bidButton);
        statusButton = new JButton("Ver Status");
        statusButton.setEnabled(false);
        bidPanel.add(statusButton);
        
        bottomPanel.add(bidPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToAuction());
        bidButton.addActionListener(e -> placeBid());
        bidField.addActionListener(e -> placeBid());
        statusButton.addActionListener(e -> requestStatus());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToAuction() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite seu nome!");
            return;
        }
        
        try {
            socket = new Socket("localhost", 5011);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            myName = name;
            setTitle("Leilão - " + myName);
            
            new Thread(() -> {
                try {
                    String message;
                    boolean nameSet = false;
                    
                    while ((message = in.readLine()) != null) {
                        final String msg = message;
                        
                        if (!nameSet && msg.contains("Digite seu nome:")) {
                            out.println(myName);
                            nameSet = true;
                            continue;
                        }
                        
                        SwingUtilities.invokeLater(() -> {
                            processMessage(msg);
                            auctionArea.append(msg + "\n");
                            auctionArea.setCaretPosition(auctionArea.getDocument().getLength());
                        });
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> {
                        auctionArea.append("\n=== Desconectado ===\n");
                        resetConnection();
                    });
                }
            }).start();
            
            connectButton.setEnabled(false);
            nameField.setEnabled(false);
            bidField.setEnabled(true);
            bidButton.setEnabled(true);
            statusButton.setEnabled(true);
            bidField.requestFocus();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar: " + e.getMessage());
        }
    }
    
    private void processMessage(String message) {
        if (message.startsWith("NOVO_LANCE:")) {
            String[] parts = message.split("R\\$ ");
            if (parts.length > 1) {
                String bidInfo = parts[1];
                String[] bidParts = bidInfo.split(" por ");
                currentBidLabel.setText("Lance Atual: R$ " + bidParts[0]);
                if (bidParts.length > 1) {
                    winnerLabel.setText("Vencedor: " + bidParts[1]);
                }
            }
        } else if (message.contains("LEILAO_FINALIZADO")) {
            bidButton.setEnabled(false);
            bidField.setEnabled(false);
        }
    }
    
    private void placeBid() {
        String bidStr = bidField.getText().trim();
        if (bidStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um valor!");
            return;
        }
        
        try {
            double bid = Double.parseDouble(bidStr);
            out.println("LANCE " + bid);
            bidField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido!");
        }
    }
    
    private void requestStatus() {
        if (out != null) {
            out.println("/status");
        }
    }
    
    private void resetConnection() {
        connectButton.setEnabled(true);
        nameField.setEnabled(true);
        bidField.setEnabled(false);
        bidButton.setEnabled(false);
        statusButton.setEnabled(false);
    }
    
    private void disconnect() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {}
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AuctionClientGUI().setVisible(true);
        });
    }
}
