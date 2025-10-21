package Ex2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Ex1.*;

public class SimpleClientTestGUI extends JFrame {

    private Socket cliente;
    private Scanner entrada;
    private PrintStream saida;

    private JTextField messageField;
    private JTextArea logArea;
    private JButton sendButton;
    private JButton clearButton;
    private JButton exitButton;

    public SimpleClientTestGUI() {
        super("Cliente Bidirecional GUI");

        // 1. Configurar a GUI
        initComponents();

        // 2. Tentar conectar ao servidor
        if (!iniciaCliente()) {
            // Se a conexão falhar, desabilita o campo de envio e o botão
            logArea.append("Falha ao conectar ao servidor.\nVerifique se o servidor está no ar.\n");
            messageField.setEnabled(false);
            sendButton.setEnabled(false);
        }
    }

    private void initComponents() {
        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Área de log (Centro)
        logArea = new JTextArea(15, 40);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel inferior para entrada e botões (Sul)
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));

        // Campo de mensagem
        messageField = new JTextField();
        southPanel.add(messageField, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5)); // 1 linha, 3 colunas
        sendButton = new JButton("Enviar");
        clearButton = new JButton("Limpar");
        exitButton = new JButton("Sair");

        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        southPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Adicionar ActionListeners
        setupActionListeners();

        // Configurações da Janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null); // Centralizar
    }

    private void setupActionListeners() {
        // Listener para o botão Sair
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerraConexaoServidor();
                System.exit(0);
            }
        });

        // Listener para o botão Limpar
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
            }
        });

        // Listener para o botão Enviar (e tecla Enter no campo de texto)
        ActionListener sendAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = messageField.getText();
                if (msg.isEmpty()) {
                    return;
                }

                if (msg.equalsIgnoreCase("sair")) {
                    encerraConexaoServidor();
                    System.exit(0);
                }

                // Desabilita botões enquanto envia
                sendButton.setEnabled(false);
                messageField.setEnabled(false);

                // Usa SwingWorker para enviar e receber em background
                new CommunicationWorker(msg).execute();
            }
        };

        sendButton.addActionListener(sendAction);
        messageField.addActionListener(sendAction); // Permite enviar com "Enter"
    }

    private boolean iniciaCliente() {
        try {
            // Usa as constantes da classe Servidor
            cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);

            // Obtem os canais de entrada e saída
            entrada = new Scanner(cliente.getInputStream());
            saida = new PrintStream(cliente.getOutputStream());

            logArea.append("Cliente conectado ao Servidor " +
                    SimpleServerTest.ENDERECO + ":" + SimpleServerTest.PORTA + "\n");
            return true;
        } catch (UnknownHostException e) {
            logArea.append("Erro: Host desconhecido. " + e.getMessage() + "\n");
            return false;
        } catch (IOException e) {
            logArea.append("Erro de E/S ao conectar: " + e.getMessage() + "\n");
            return false;
        }
    }

    private void encerraConexaoServidor() {
        try {
            if (saida != null) saida.close();
            if (entrada != null) entrada.close();
            if (cliente != null && !cliente.isClosed()) cliente.close();
            logArea.append("Cliente desconectado do Servidor!\n");
        } catch (IOException e) {
            logArea.append("Erro ao fechar conexão: " + e.getMessage() + "\n");
        }
    }

    // SwingWorker para lidar com a comunicação de rede fora da Event Dispatch Thread (EDT)
    private class CommunicationWorker extends SwingWorker<String, Void> {
        private String msgEnviada;

        public CommunicationWorker(String msg) {
            this.msgEnviada = msg;
        }

        @Override
        protected String doInBackground() throws Exception {
            // 1. Envia mensagem ao servidor
            saida.println(msgEnviada);
            // 2. Aguarda (bloqueia) a resposta do servidor
            return entrada.nextLine();
        }

        @Override
        protected void done() {
            try {
                // 3. Pega o resultado do doInBackground()
                String echo = get();

                // 4. Atualiza a GUI (agora de volta na EDT)
                logArea.append("[Enviado]: " + msgEnviada + "\n");
                logArea.append("[Recebido]: " + echo + "\n");

                // 5. Verifica a comunicação
                if (echo.equals(msgEnviada)) {
                    logArea.append("[Status]: Comunicação OK!\n\n");
                } else {
                    logArea.append("[Status]: Comunicação com problema!\n\n");
                }

            } catch (InterruptedException | ExecutionException e) {
                logArea.append("Erro na comunicação: " + e.getMessage() + "\n");
                // Se a comunicação falhar, pode ser que o servidor caiu.
                // Pode-se tentar reconectar ou apenas desabilitar os botões.
            } finally {
                // 6. Reabilita os campos, independentemente do resultado
                messageField.setText("");
                messageField.setEnabled(true);
                sendButton.setEnabled(true);
                messageField.requestFocus();
            }
        }
    }


    public static void main(String args[]) {
        // Executa a GUI na Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleClientTestGUI().setVisible(true);
            }
        });
    }
}
