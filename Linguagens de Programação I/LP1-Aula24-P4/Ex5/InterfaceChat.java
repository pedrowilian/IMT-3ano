import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class InterfaceChat extends JFrame {

    private ClienteBatepapo clienteBatepapo;
    private final JScrollPane scrollPainelMensagens = new JScrollPane();
    private final JTextArea areaMensagens = new JTextArea();
    private final JTextField campoTexto = new JTextField();
    private final JButton botaoEnviar = new JButton();
    private final JButton botaoLimpar = new JButton();
    private final JButton botaoSair = new JButton();
    private final ArrayList<String> historicoMensagens = new ArrayList<>();

    public InterfaceChat() {
        inicializarComponentes();
    }

    public void iniciar(ClienteBatepapo clienteBatepapo) {
        try {
            this.clienteBatepapo = clienteBatepapo;
            clienteBatepapo.start(areaMensagens, this); 
            this.setVisible(true);
            adicionarMensagem("Conexão estabelecida com o servidor!");
        } catch (Exception e) {
            System.out.println("Falha ao iniciar cliente: " + e.getMessage());
        }
    }

    private void inicializarComponentes() {
        setTitle("Sala de Conversa Simples");
        setLayout(new BorderLayout());
        
        setPreferredSize(new Dimension(450, 480)); 

        areaMensagens.setColumns(20);
        areaMensagens.setRows(5);
        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        areaMensagens.setWrapStyleWord(true);
        scrollPainelMensagens.setViewportView(areaMensagens);
        
        JPanel painelEntrada = new JPanel(new BorderLayout(5, 0));
        botaoEnviar.setText("Enviar");
        painelEntrada.add(campoTexto, BorderLayout.CENTER);
        painelEntrada.add(botaoEnviar, BorderLayout.EAST);

        JPanel painelControle = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        botaoLimpar.setText("Limpar Histórico");
        botaoSair.setText("Fechar Chat");
        painelControle.add(botaoLimpar);
        painelControle.add(botaoSair);
        
        JPanel painelInferior = new JPanel(new GridLayout(2, 1));
        painelInferior.add(painelEntrada);
        painelInferior.add(painelControle);
        
        add(scrollPainelMensagens, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);


        botaoEnviar.addActionListener(this::acaoBotaoEnviar);
        campoTexto.addActionListener(this::acaoCampoTexto);
        botaoLimpar.addActionListener(this::acaoBotaoLimpar);
        botaoSair.addActionListener(this::acaoBotaoSair);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                rotinaSaida(); 
            }
        });
        
        pack(); 
        setLocationRelativeTo(null); 
    }

    private void acaoBotaoEnviar(ActionEvent evt) {
        enviarMensagem(campoTexto.getText());
    }

    private void acaoCampoTexto(ActionEvent evt) {
        enviarMensagem(campoTexto.getText());
    }

    private void acaoBotaoLimpar(ActionEvent evt) {
        limparHistorico();
    }

    private void acaoBotaoSair(ActionEvent evt) {
        rotinaSaida();
    }

    private void enviarMensagem(String mensagem) {
        if (mensagem != null && !mensagem.trim().isEmpty()) {
            clienteBatepapo.sendMsg(mensagem.trim());
            campoTexto.setText("");
            adicionarMensagem("Eu: " + mensagem.trim());
        }
    }

    private void rotinaSaida() {
        if (clienteBatepapo != null) {
            clienteBatepapo.sendMsg("desconectar"); 
            clienteBatepapo.close(); 
        }
        this.dispose();
        System.exit(0); 
    }

    public void adicionarMensagem(String mensagem) {
        historicoMensagens.add(mensagem);
        areaMensagens.append(mensagem + "\n");
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }

    public void adicionarMensagemServidor(String mensagem) {
        historicoMensagens.add(mensagem);
        areaMensagens.append(mensagem + "\n");
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }

    public void limparHistorico() {
        historicoMensagens.clear();
        areaMensagens.setText("");
    }
}