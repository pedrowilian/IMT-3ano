import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class FileClientGUI extends JFrame {
    private JTextArea contentArea;
    private JTextField fileNameField;
    private JButton getButton;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public FileClientGUI() {
        setTitle("File Client");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Area de conteudo
        contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Nome do arquivo:"));
        fileNameField = new JTextField(20);
        fileNameField.setEnabled(false);
        topPanel.add(fileNameField);
        getButton = new JButton("Buscar Arquivo");
        getButton.setEnabled(false);
        topPanel.add(getButton);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        getButton.addActionListener(e -> requestFile());
        fileNameField.addActionListener(e -> requestFile());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5005);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            contentArea.append("=== Conectado ao servidor de arquivos ===\n");
            contentArea.append("Digite o nome do arquivo e clique em 'Buscar'\n\n");
            
            connectButton.setEnabled(false);
            fileNameField.setEnabled(true);
            getButton.setEnabled(true);
            fileNameField.requestFocus();
            
        } catch (IOException e) {
            contentArea.append("Erro ao conectar: " + e.getMessage() + "\n");
        }
    }
    
    private void requestFile() {
        String fileName = fileNameField.getText().trim();
        if (fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do arquivo!");
            return;
        }
        
        contentArea.setText("");
        contentArea.append("Solicitando arquivo: " + fileName + "\n");
        contentArea.append("=" .repeat(50) + "\n\n");
        
        out.println("GET " + fileName);
        
        // Thread para receber conteúdo do arquivo
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("FIM_ARQUIVO")) {
                        SwingUtilities.invokeLater(() -> 
                            contentArea.append("\n" + "=".repeat(50) + "\n=== Fim do arquivo ===\n"));
                        break;
                    }
                    final String content = line;
                    SwingUtilities.invokeLater(() -> contentArea.append(content + "\n"));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> 
                    contentArea.append("\nErro ao receber arquivo: " + e.getMessage() + "\n"));
            }
        }).start();
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
            new FileClientGUI().setVisible(true);
        });
    }
}
