package Ex5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ChatScreen extends JFrame {

    private Ex5ClienteBatepapo clienteBatepapo;
    // JOptionPane chatScreen = new JOptionPane(); // Não é necessário instanciar isso aqui
    JButton sendButton = new JButton();
    JButton clearButton = new JButton();
    JButton exitButton = new JButton();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    ArrayList<String> messages = new ArrayList<String>();

    public ChatScreen() {
    }

    public void start(Ex5ClienteBatepapo clienteBatepapo) throws IOException {
        try {
            this.clienteBatepapo = clienteBatepapo;
            clienteBatepapo.start(textArea, this);
            build();
            addMessage("Conectado ao servidor!");
        }
        catch ( Exception e ){
            System.out.println("Erro ao iniciar cliente: " + e.getMessage());
        }
    }

    public void build() {
        setSize(500, 500);
        setVisible(true);
        // Mudei para DO_NOTHING para controlarmos a saída manualmente no WindowListener
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setTitle("Chat Screen");
        
        // Adiciona componentes (mantido igual ao seu)
        add(textField);
        add(textArea);
        add(sendButton);
        add(clearButton);
        add(exitButton);

        sendButton.setText("Send");
        clearButton.setText("Clear");
        exitButton.setText("Exit");

        // Action Listeners
        sendButton.addActionListener(evt -> {
            try {
                sendButtonActionPerformed(evt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        clearButton.addActionListener(evt -> clearButtonActionPerformed(evt));

        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));

        textField.addActionListener(evt -> {
            try {
                textFieldActionPerformed(evt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Layout (Mantido igual ao seu código original, omitido para brevidade, mas deve estar aqui)
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(textArea)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(exitButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        
        // Tratamento ao clicar no "X" da janela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                exitRoutine();
            }
        });
    }

    // Criei uma rotina única de saída para ser usada pelo botão e pelo "X"
    private void exitRoutine() {
        if (clienteBatepapo != null) {
            clienteBatepapo.sendMsg("sair");
            clienteBatepapo.close();
        }
        this.dispose();
        System.exit(0);
    }

    private void exitButtonActionPerformed(ActionEvent evt) {
        // --- CORREÇÃO AQUI ---
        // Removido o Thread.sleep que travava a UI
        exitRoutine();
    }

    private void textFieldActionPerformed(ActionEvent evt) throws IOException {
        send(textField.getText());
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        clearMessages();
    }

    private void sendButtonActionPerformed(ActionEvent evt) throws IOException {
        send(textField.getText());
    }

    private void send(String msg) throws IOException {
        if (!msg.isEmpty()) { // Pequena proteção para não enviar vazio
            clienteBatepapo.sendMsg(msg);
            textField.setText("");
            addMessage("Você: " + msg); // Identifica que foi você
        }
    }

    public void addMessage(String msg) {
        messages.add(msg);
        textArea.append(msg + "\n");
        // Auto-scroll para a última mensagem
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void addServerMessage(String msg) {
        messages.add(msg);
        textArea.append(msg + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void clearMessages() {
        messages.clear();
        textArea.setText("");
    }
}