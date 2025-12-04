import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class SensorClientGUI extends JFrame {
    private JTextArea stateArea;
    private JTextField nameField, typeField, valueField;
    private JButton connectButton, sendButton;
    private JCheckBox autoSendCheck;
    private Socket socket;
    private PrintWriter out;
    private Timer autoTimer;
    
    public SensorClientGUI() {
        setTitle("Cliente de Sensor");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nome do Sensor:"));
        nameField = new JTextField(10);
        topPanel.add(nameField);
        connectButton = new JButton("Conectar");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);
        
        stateArea = new JTextArea();
        stateArea.setEditable(false);
        stateArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        add(new JScrollPane(stateArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel hint = new JLabel("Ex: TEMP 23.5 | UMID 60 | PRES 1013");
        hint.setFont(new Font("SansSerif", Font.ITALIC, 11));
        bottomPanel.add(hint, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        typeField = new JTextField(6);
        typeField.setEnabled(false);
        inputPanel.add(new JLabel("Tipo:"));
        inputPanel.add(typeField);
        valueField = new JTextField(8);
        valueField.setEnabled(false);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valueField);
        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);
        inputPanel.add(sendButton);
        autoSendCheck = new JCheckBox("Auto-envio (5s)");
        autoSendCheck.setEnabled(false);
        inputPanel.add(autoSendCheck);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(e -> connect());
        sendButton.addActionListener(e -> sendData());
        autoSendCheck.addActionListener(e -> toggleAutoSend());
        
        autoTimer = new Timer(5000, e -> sendData());
        autoTimer.setRepeats(true);
    }
    
    private void connect() {
        try {
            socket = new Socket("localhost", 5020);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(() -> {
                try {
                    String msg;
                    boolean nameSet = false;
                    while ((msg = in.readLine()) != null) {
                        if (!nameSet && msg.contains("sensor:")) {
                            out.println(nameField.getText());
                            nameSet = true;
                            continue;
                        }
                        final String m = msg;
                        SwingUtilities.invokeLater(() -> {
                            stateArea.setText(m);
                        });
                    }
                } catch (IOException e) {}
            }).start();
            
            connectButton.setEnabled(false);
            typeField.setEnabled(true);
            valueField.setEnabled(true);
            sendButton.setEnabled(true);
            autoSendCheck.setEnabled(true);
        } catch (IOException e) {}
    }
    
    private void sendData() {
        String type = typeField.getText().trim();
        String value = valueField.getText().trim();
        if (!type.isEmpty() && !value.isEmpty() && out != null) {
            out.println(type + " " + value);
        }
    }
    
    private void toggleAutoSend() {
        if (autoSendCheck.isSelected()) {
            autoTimer.start();
        } else {
            autoTimer.stop();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SensorClientGUI().setVisible(true));
    }
}
