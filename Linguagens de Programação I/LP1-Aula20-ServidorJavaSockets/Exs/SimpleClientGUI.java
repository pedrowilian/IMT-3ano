import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class SimpleClientGUI extends JFrame {
    // Componentes da interface
    private JTextField txtMensagem;
    private JTextArea txtStatus;
    private JButton btnEnviar;
    private JButton btnLimpar;
    private JButton btnSair;
    private JButton btnConectar;
    private JTextField txtServidor;
    private JTextField txtPorta;
    
    // Componentes de rede
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private boolean conectado = false;
    
    public SimpleClientGUI() {
        // Configuracoes da janela
        setTitle("Cliente TCP - Interface Grafica");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Adicionar listeners
        adicionarListeners();
    }
    
    private void inicializarComponentes() {
        // Painel principal com BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // ===== PAINEL SUPERIOR - Conexao =====
        JPanel painelConexao = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelConexao.setBorder(BorderFactory.createTitledBorder("Conexao"));
        
        painelConexao.add(new JLabel("Servidor:"));
        txtServidor = new JTextField("localhost", 15);
        painelConexao.add(txtServidor);
        
        painelConexao.add(new JLabel("Porta:"));
        txtPorta = new JTextField("6789", 6);
        painelConexao.add(txtPorta);
        
        btnConectar = new JButton("Conectar");
        btnConectar.setBackground(new Color(76, 175, 80));
        btnConectar.setForeground(Color.WHITE);
        btnConectar.setFocusPainted(false);
        painelConexao.add(btnConectar);
        
        add(painelConexao, BorderLayout.NORTH);
        
        // ===== PAINEL CENTRAL - Mensagem e Status =====
        JPanel painelCentral = new JPanel(new BorderLayout(5, 5));
        
        // Subpainel para entrada de mensagem
        JPanel painelMensagem = new JPanel(new BorderLayout(5, 5));
        painelMensagem.setBorder(BorderFactory.createTitledBorder("Mensagem para Enviar"));
        
        txtMensagem = new JTextField();
        txtMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMensagem.setEnabled(false);
        painelMensagem.add(txtMensagem, BorderLayout.CENTER);
        
        painelCentral.add(painelMensagem, BorderLayout.NORTH);
        
        // Subpainel para status
        JPanel painelStatus = new JPanel(new BorderLayout(5, 5));
        painelStatus.setBorder(BorderFactory.createTitledBorder("Status da Comunicacao"));
        
        txtStatus = new JTextArea();
        txtStatus.setEditable(false);
        txtStatus.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        txtStatus.setLineWrap(true);
        txtStatus.setWrapStyleWord(true);
        
        JScrollPane scrollStatus = new JScrollPane(txtStatus);
        scrollStatus.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        painelStatus.add(scrollStatus, BorderLayout.CENTER);
        
        painelCentral.add(painelStatus, BorderLayout.CENTER);
        
        add(painelCentral, BorderLayout.CENTER);
        
        // ===== PAINEL INFERIOR - Botões =====
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        btnEnviar = new JButton("Enviar");
        btnEnviar.setPreferredSize(new Dimension(100, 35));
        btnEnviar.setBackground(new Color(33, 150, 243));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFocusPainted(false);
        btnEnviar.setEnabled(false);
        painelBotoes.add(btnEnviar);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setPreferredSize(new Dimension(100, 35));
        btnLimpar.setBackground(new Color(255, 152, 0));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setFocusPainted(false);
        painelBotoes.add(btnLimpar);
        
        btnSair = new JButton("Sair");
        btnSair.setPreferredSize(new Dimension(100, 35));
        btnSair.setBackground(new Color(244, 67, 54));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        painelBotoes.add(btnSair);
        
        add(painelBotoes, BorderLayout.SOUTH);
        
        // Mensagem inicial
        adicionarStatus("Sistema: Aplicacao iniciada. Aguardando conexao...\n");
    }
    
    private void adicionarListeners() {
        // Botão Conectar
        btnConectar.addActionListener(e -> conectarAoServidor());
        
        // Botão Enviar
        btnEnviar.addActionListener(e -> enviarMensagem());
        
        // Enter no campo de mensagem
        txtMensagem.addActionListener(e -> enviarMensagem());
        
        // Botão Limpar
        btnLimpar.addActionListener(e -> limparCampos());
        
        // Botão Sair
        btnSair.addActionListener(e -> sairAplicacao());
        
        // Fechar janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sairAplicacao();
            }
        });
    }
    
    private void conectarAoServidor() {
        if (conectado) {
            desconectar();
            return;
        }
        
        String servidor = txtServidor.getText().trim();
        String portaStr = txtPorta.getText().trim();
        
        if (servidor.isEmpty() || portaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, preencha o servidor e a porta!",
                "Erro de Validacao",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int porta = Integer.parseInt(portaStr);
            
            adicionarStatus("Sistema: Conectando ao servidor " + servidor + ":" + porta + "...\n");
            
            // Criar socket de conexão
            clientSocket = new Socket(servidor, porta);
            
            // Criar streams
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );
            
            conectado = true;
            
            adicionarStatus("Sistema: [OK] Conectado com sucesso!\n");
            adicionarStatus("========================================\n");
            
            // Atualizar interface
            btnConectar.setText("Desconectar");
            btnConectar.setBackground(new Color(244, 67, 54));
            txtServidor.setEnabled(false);
            txtPorta.setEnabled(false);
            txtMensagem.setEnabled(true);
            btnEnviar.setEnabled(true);
            txtMensagem.requestFocus();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Porta invalida! Digite um numero.",
                "Erro de Validacao",
                JOptionPane.ERROR_MESSAGE);
        } catch (UnknownHostException ex) {
            adicionarStatus("Erro: Servidor nao encontrado!\n");
            JOptionPane.showMessageDialog(this,
                "Servidor nao encontrado: " + servidor,
                "Erro de Conexao",
                JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            adicionarStatus("Erro: Nao foi possivel conectar ao servidor!\n");
            adicionarStatus("Detalhes: " + ex.getMessage() + "\n");
            JOptionPane.showMessageDialog(this,
                "Erro ao conectar:\n" + ex.getMessage(),
                "Erro de Conexao",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void desconectar() {
        try {
            if (conectado) {
                // Enviar comando de desconexão
                if (outToServer != null) {
                    outToServer.writeBytes("-1\n");
                    
                    // Ler resposta de confirmação do servidor
                    if (inFromServer != null) {
                        String resposta = inFromServer.readLine();
                        if (resposta != null) {
                            adicionarStatus("Servidor: " + resposta + "\n");
                        }
                    }
                }
                
                // Fechar conexões
                if (inFromServer != null) inFromServer.close();
                if (outToServer != null) outToServer.close();
                if (clientSocket != null) clientSocket.close();
                
                adicionarStatus("Sistema: Desconectado do servidor.\n");
                adicionarStatus("========================================\n");
            }
        } catch (IOException ex) {
            adicionarStatus("Erro ao desconectar: " + ex.getMessage() + "\n");
        } finally {
            conectado = false;
            
            // Atualizar interface
            btnConectar.setText("Conectar");
            btnConectar.setBackground(new Color(76, 175, 80));
            txtServidor.setEnabled(true);
            txtPorta.setEnabled(true);
            txtMensagem.setEnabled(false);
            btnEnviar.setEnabled(false);
            txtMensagem.setText("");
        }
    }
    
    private void enviarMensagem() {
        if (!conectado) {
            JOptionPane.showMessageDialog(this,
                "Nao esta conectado ao servidor!",
                "Erro",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String mensagem = txtMensagem.getText().trim();
        
        if (mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Digite uma mensagem para enviar!",
                "Erro de Validacao",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Enviar mensagem ao servidor
            adicionarStatus("Cliente -> Servidor: " + mensagem + "\n");
            outToServer.writeBytes(mensagem + "\n");
            
            // Receber resposta do servidor
            String resposta = inFromServer.readLine();
            
            if (resposta != null) {
                adicionarStatus("Servidor -> Cliente: " + resposta + "\n");
                adicionarStatus("========================================\n");
            } else {
                adicionarStatus("Erro: Servidor desconectou!\n");
                desconectar();
            }
            
            // Limpar campo de mensagem
            txtMensagem.setText("");
            txtMensagem.requestFocus();
            
        } catch (IOException ex) {
            adicionarStatus("Erro ao enviar mensagem: " + ex.getMessage() + "\n");
            JOptionPane.showMessageDialog(this,
                "Erro ao comunicar com o servidor:\n" + ex.getMessage(),
                "Erro de Comunicacao",
                JOptionPane.ERROR_MESSAGE);
            desconectar();
        }
    }
    
    private void limparCampos() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja limpar o historico de mensagens?",
            "Confirmar Limpeza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            txtStatus.setText("");
            txtMensagem.setText("");
            adicionarStatus("Sistema: Historico limpo.\n");
            adicionarStatus("========================================\n");
        }
    }
    
    private void sairAplicacao() {
        if (conectado) {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                "Voce esta conectado ao servidor.\nDeseja desconectar e sair?",
                "Confirmar Saida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                desconectar();
                adicionarStatus("Sistema: Encerrando aplicacao...\n");
                System.exit(0);
            }
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair da aplicacao?",
                "Confirmar Saida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                adicionarStatus("Sistema: Encerrando aplicacao...\n");
                System.exit(0);
            }
        }
    }
    
    private void adicionarStatus(String mensagem) {
        txtStatus.append(mensagem);
        // Auto-scroll para o final
        txtStatus.setCaretPosition(txtStatus.getDocument().getLength());
    }
    
    public static void main(String[] args) {
        // Configurar Look and Feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Criar e exibir a interface
        SwingUtilities.invokeLater(() -> {
            SimpleClientGUI gui = new SimpleClientGUI();
            gui.setVisible(true);
        });
    }
}
