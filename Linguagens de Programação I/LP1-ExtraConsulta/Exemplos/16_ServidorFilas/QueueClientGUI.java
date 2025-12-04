import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class QueueClientGUI extends JFrame {
    private JTextArea queueArea;
    private JTextField nameField, itemField;
    private JButton connectButton, pushButton, popButton, sizeButton;
    private Socket socket;
    private PrintWriter out;
    
    public QueueClientGUI() {
        setTitle("Sistema de Filas");
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
        
        queueArea = new JTextArea();
        queueArea.setEditable(false);
        queueArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(queueArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        itemField = new JTextField(15);
        itemField.setEnabled(false);
        bottomPanel.add(itemField);
        pushButton = new JButton("PUSH");
        pushButton.setEnabled(false);
        bottomPanel.add(pushButton);
        popButton = new JButton("POP");
        popButton.setEnabled(false);
        bottomPanel.add(popButton);
        sizeButton = new JButton("SIZE");
        sizeButton.setEnabled(false);
        bottomPanel.add(sizeButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        pushButton.addActionListener(e -> push());
        popButton.addActionListener(e -> {if(out!=null) out.println("POP");});
        sizeButton.addActionListener(e -> {if(out!=null) out.println("SIZE");});
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5015);
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
                        SwingUtilities.invokeLater(() -> queueArea.append(m + "\n"));
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            itemField.setEnabled(true);
            pushButton.setEnabled(true);
            popButton.setEnabled(true);
            sizeButton.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void push() {
        String item = itemField.getText().trim();
        if (!item.isEmpty()) {
            out.println("PUSH " + item);
            itemField.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QueueClientGUI().setVisible(true));
    }
}
