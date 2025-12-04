import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MathClientGUI extends JFrame {
    private JTextArea resultArea;
    private JTextField num1Field, num2Field;
    private JComboBox<String> operationCombo;
    private JButton calculateButton;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public MathClientGUI() {
        setTitle("Calculadora - Cliente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Area de resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Número 1:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        num1Field = new JTextField(10);
        num1Field.setEnabled(false);
        inputPanel.add(num1Field, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Operação:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        operationCombo = new JComboBox<>(new String[]{"SOMA", "SUB", "MULT", "DIV"});
        operationCombo.setEnabled(false);
        inputPanel.add(operationCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Número 2:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        num2Field = new JTextField(10);
        num2Field.setEnabled(false);
        inputPanel.add(num2Field, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        calculateButton = new JButton("Calcular");
        calculateButton.setEnabled(false);
        inputPanel.add(calculateButton, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        connectButton = new JButton("Conectar ao Servidor");
        inputPanel.add(connectButton, gbc);
        
        add(inputPanel, BorderLayout.NORTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        calculateButton.addActionListener(e -> calculate());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5004);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            resultArea.append("Conectado ao servidor!\n");
            resultArea.append("Operações: SOMA, SUB, MULT, DIV\n\n");
            
            connectButton.setEnabled(false);
            num1Field.setEnabled(true);
            num2Field.setEnabled(true);
            operationCombo.setEnabled(true);
            calculateButton.setEnabled(true);
            num1Field.requestFocus();
            
            // Thread para receber respostas
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        final String message = msg;
                        SwingUtilities.invokeLater(() -> resultArea.append("Resultado: " + message + "\n\n"));
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> resultArea.append("Desconectado.\n"));
                }
            }).start();
            
        } catch (IOException e) {
            resultArea.append("Erro ao conectar: " + e.getMessage() + "\n");
        }
    }
    
    private void calculate() {
        try {
            String num1 = num1Field.getText().trim();
            String num2 = num2Field.getText().trim();
            String operation = (String) operationCombo.getSelectedItem();
            
            if (num1.isEmpty() || num2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha ambos os números!");
                return;
            }
            
            String command = operation + " " + num1 + " " + num2;
            resultArea.append("Comando: " + command + "\n");
            out.println(command);
            
            num1Field.setText("");
            num2Field.setText("");
            num1Field.requestFocus();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    
    private void disconnect() {
        try {
            if (out != null) out.println("SAIR");
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MathClientGUI().setVisible(true);
        });
    }
}
