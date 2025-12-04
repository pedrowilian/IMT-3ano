import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class VotingClientGUI extends JFrame {
    private JTextArea resultArea;
    private JTextField nameField;
    private JButton connectButton, resultButton;
    private JRadioButton optA, optB, optC, optD;
    private JButton voteButton;
    private Socket socket;
    private PrintWriter out;
    
    public VotingClientGUI() {
        setTitle("Sistema de Votação");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nome:"));
        nameField = new JTextField(15);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        resultButton = new JButton("Ver Resultado");
        resultButton.setEnabled(false);
        topPanel.add(resultButton);
        add(topPanel, BorderLayout.NORTH);
        
        JPanel votePanel = new JPanel(new GridLayout(5, 1));
        votePanel.setBorder(BorderFactory.createTitledBorder("Sua Votação"));
        ButtonGroup group = new ButtonGroup();
        optA = new JRadioButton("A) Java");
        optB = new JRadioButton("B) Python");
        optC = new JRadioButton("C) JavaScript");
        optD = new JRadioButton("D) C++");
        group.add(optA); group.add(optB); group.add(optC); group.add(optD);
        votePanel.add(optA);
        votePanel.add(optB);
        votePanel.add(optC);
        votePanel.add(optD);
        voteButton = new JButton("VOTAR");
        voteButton.setEnabled(false);
        voteButton.setBackground(new Color(0, 128, 0));
        voteButton.setForeground(Color.WHITE);
        votePanel.add(voteButton);
        add(votePanel, BorderLayout.WEST);
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        connectButton.addActionListener(e -> connect());
        voteButton.addActionListener(e -> vote());
        resultButton.addActionListener(e -> {if(out!=null) out.println("/resultado");});
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5014);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        if (!nameSet && msg.contains("Nome:")) {
                            out.println(nameField.getText());
                            nameSet = true;
                            continue;
                        }
                        final String m = msg;
                        SwingUtilities.invokeLater(() -> resultArea.append(m + "\n"));
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            voteButton.setEnabled(true);
            resultButton.setEnabled(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar!");
        }
    }
    
    private void vote() {
        String vote = "";
        if (optA.isSelected()) vote = "A";
        else if (optB.isSelected()) vote = "B";
        else if (optC.isSelected()) vote = "C";
        else if (optD.isSelected()) vote = "D";
        
        if (!vote.isEmpty()) {
            out.println("VOTO " + vote);
            voteButton.setEnabled(false);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VotingClientGUI().setVisible(true));
    }
}
