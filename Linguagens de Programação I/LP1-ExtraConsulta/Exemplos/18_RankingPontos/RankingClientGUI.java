import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class RankingClientGUI extends JFrame {
    private JTextArea rankingArea;
    private JTextField nameField, pointsField;
    private JButton connectButton, addButton, subButton, getButton, rankButton;
    private Socket socket;
    private PrintWriter out;
    
    public RankingClientGUI() {
        setTitle("Sistema de Ranking");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nome:"));
        nameField = new JTextField(10);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(rankingArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        pointsField = new JTextField(8);
        pointsField.setEnabled(false);
        bottomPanel.add(pointsField);
        addButton = new JButton("ADD");
        addButton.setEnabled(false);
        bottomPanel.add(addButton);
        subButton = new JButton("SUB");
        subButton.setEnabled(false);
        bottomPanel.add(subButton);
        getButton = new JButton("GET");
        getButton.setEnabled(false);
        bottomPanel.add(getButton);
        rankButton = new JButton("RANK");
        rankButton.setEnabled(false);
        bottomPanel.add(rankButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        addButton.addActionListener(e -> sendCommand("ADD"));
        subButton.addActionListener(e -> sendCommand("SUB"));
        getButton.addActionListener(e -> {if(out!=null) out.println("GET");});
        rankButton.addActionListener(e -> {if(out!=null) out.println("RANK");});
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5017);
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
                        SwingUtilities.invokeLater(() -> rankingArea.append(m + "\n"));
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            pointsField.setEnabled(true);
            addButton.setEnabled(true);
            subButton.setEnabled(true);
            getButton.setEnabled(true);
            rankButton.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void sendCommand(String cmd) {
        String points = pointsField.getText().trim();
        if (!points.isEmpty()) {
            out.println(cmd + " " + points);
            pointsField.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RankingClientGUI().setVisible(true));
    }
}
