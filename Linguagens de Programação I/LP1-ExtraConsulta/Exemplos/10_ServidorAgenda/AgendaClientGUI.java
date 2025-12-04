import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class AgendaClientGUI extends JFrame {
    private JTextArea agendaArea;
    private JTextField descriptionField;
    private JTextField timeField;
    private JTextField indexField;
    private JButton addButton;
    private JButton listButton;
    private JButton deleteButton;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public AgendaClientGUI() {
        setTitle("Agenda Client");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Area de exibição da agenda
        agendaArea = new JTextArea();
        agendaArea.setEditable(false);
        agendaArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(agendaArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de controles
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Adicionar compromisso
        gbc.gridx = 0; gbc.gridy = 0;
        controlPanel.add(new JLabel("Descrição:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        descriptionField = new JTextField(20);
        descriptionField.setEnabled(false);
        controlPanel.add(descriptionField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        controlPanel.add(new JLabel("Horário:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        timeField = new JTextField(10);
        timeField.setEnabled(false);
        controlPanel.add(timeField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        addButton = new JButton("Adicionar");
        addButton.setEnabled(false);
        controlPanel.add(addButton, gbc);
        
        // Deletar compromisso
        gbc.gridx = 0; gbc.gridy = 2;
        controlPanel.add(new JLabel("Indice:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        indexField = new JTextField(10);
        indexField.setEnabled(false);
        controlPanel.add(indexField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 2;
        deleteButton = new JButton("Deletar");
        deleteButton.setEnabled(false);
        controlPanel.add(deleteButton, gbc);
        
        // Botões de ação
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        JPanel actionPanel = new JPanel(new FlowLayout());
        listButton = new JButton("Listar Agenda");
        listButton.setEnabled(false);
        connectButton = new JButton("Conectar ao Servidor");
        actionPanel.add(listButton);
        actionPanel.add(connectButton);
        controlPanel.add(actionPanel, gbc);
        
        add(controlPanel, BorderLayout.NORTH);
        
        // Ações
        connectButton.addActionListener(e -> connectToServer());
        addButton.addActionListener(e -> addAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
        listButton.addActionListener(e -> listAppointments());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnect();
            }
        });
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5009);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            agendaArea.append("=== Conectado ao Servidor de Agenda ===\n");
            agendaArea.append("Comandos disponíveis:\n");
            agendaArea.append("  - ADD <descrição> <horário>\n");
            agendaArea.append("  - LIST\n");
            agendaArea.append("  - DEL <índice>\n\n");
            
            connectButton.setEnabled(false);
            descriptionField.setEnabled(true);
            timeField.setEnabled(true);
            indexField.setEnabled(true);
            addButton.setEnabled(true);
            deleteButton.setEnabled(true);
            listButton.setEnabled(true);
            
            // Thread para receber respostas
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        final String message = msg;
                        SwingUtilities.invokeLater(() -> agendaArea.append(message + "\n"));
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> agendaArea.append("\n=== Desconectado ===\n"));
                }
            }).start();
            
        } catch (IOException e) {
            agendaArea.append("Erro ao conectar: " + e.getMessage() + "\n");
        }
    }
    
    private void addAppointment() {
        String description = descriptionField.getText().trim();
        String time = timeField.getText().trim();
        
        if (description.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha descrição e horário!");
            return;
        }
        
        String command = "ADD " + description + " " + time;
        agendaArea.append("\n> " + command + "\n");
        out.println(command);
        
        descriptionField.setText("");
        timeField.setText("");
        descriptionField.requestFocus();
    }
    
    private void deleteAppointment() {
        String index = indexField.getText().trim();
        
        if (index.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o índice para deletar!");
            return;
        }
        
        String command = "DEL " + index;
        agendaArea.append("\n> " + command + "\n");
        out.println(command);
        
        indexField.setText("");
    }
    
    private void listAppointments() {
        agendaArea.append("\n> LIST\n");
        out.println("LIST");
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
            new AgendaClientGUI().setVisible(true);
        });
    }
}
