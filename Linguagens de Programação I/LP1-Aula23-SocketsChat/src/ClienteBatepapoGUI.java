import javax.swing.*;        
import java.awt.*;           
import java.io.IOException;  

public class ClienteBatepapoGUI extends JFrame implements Runnable {
    //SocketCliente — classe que faz a comunicação com o servidor
    private SocketCliente clientSocket;

    //Componentes gráficos principais
    private JTextArea areaMensagens;    //Exibe o histórico de mensagens
    private JTextField campoMensagem;   //Campo de texto para digitar mensagens
    private JButton botaoEnviar;        //Botão para enviar mensagens

    //Thread que ficará escutando mensagens do servidor
    private Thread threadLeitura;

    public ClienteBatepapoGUI() {
        super("Bate-Papo - Cliente");  //Título da janela

        /***************----- Área de mensagens -----***************/
        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);              //Usuário não pode editar o histórico
        areaMensagens.setLineWrap(true);            //Quebra automática de linha
        areaMensagens.setWrapStyleWord(true);       //Não corta palavras

        //Adiciona barra de rolagem à área de texto
        JScrollPane scroll = new JScrollPane(areaMensagens);

        /****************----- Campo de entrada e botão -----***************/
        campoMensagem = new JTextField();                   //Campo onde o usuário digita a mensagem
        botaoEnviar = new JButton("Enviar");           //Botão para enviar mensagem

        //Painel inferior com layout horizontal (campo + botão)
        JPanel painelInferior = new JPanel(new BorderLayout(7, 7));
        painelInferior.add(campoMensagem, BorderLayout.CENTER);
        painelInferior.add(botaoEnviar, BorderLayout.EAST);

        /***************----- Montagem do layout da janela -----***************/
        getContentPane().setLayout(new BorderLayout(5, 5));
        getContentPane().add(scroll, BorderLayout.CENTER);              //Área de mensagens no centro
        getContentPane().add(painelInferior, BorderLayout.SOUTH);       //Campo e botão na parte inferior

        //Define o tamanho da janela e outras propriedades
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                                  //Centraliza na tela

        /***************----- Eventos de envio -----***************/
        //Quando o usuário clicar no botão "Enviar"
        botaoEnviar.addActionListener(e -> enviarMensagem());
        //Quando pressionar Enter no campo de texto
        campoMensagem.addActionListener(e -> enviarMensagem());
    }

    
    //Método que inicia a conexão com o servidor e exibe a janela.
    public void iniciar() {
        try {
            //Cria um novo socket e conecta ao servidor
            clientSocket = new SocketCliente(
                new java.net.Socket(ServidorBatepapo.ADDRESS, ServidorBatepapo.PORT)
            );

            //Inicia a thread que ficará lendo mensagens do servidor
            threadLeitura = new Thread(this);
            threadLeitura.start();

            //Exibe a janela na tela
            setVisible(true);

            //Mostra mensagem de status inicial
            appendMsg("Conectado ao servidor " + ServidorBatepapo.ADDRESS + ":" + ServidorBatepapo.PORT);
        } catch (IOException ex) {
            //Caso a conexão falhe, mostra erro e encerra
            JOptionPane.showMessageDialog(this, "Erro ao conectar: " + ex.getMessage(),
                                          "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    //Envia mensagem digitada pelo usuário para o servidor 
    private void enviarMensagem(){
        String msg = campoMensagem.getText().trim(); //Lê o texto e remove espaços extras
        if (msg.isEmpty()) return;                   //Ignora mensagens vazias

        appendMsg("<Você>: "+msg);                   //Exibe a mensagem na interface gráfica
        clientSocket.sendMsg(msg);                   //Envia mensagem via socket
        //Caso o usuário digite "sair", fecha a conexão e encerra o programa
        if (msg.equalsIgnoreCase("sair")){
            fecharConexao();
            System.exit(0);
        }
        campoMensagem.setText("");                   //Limpa o campo após enviar
    }

    /*
     * Exibe uma mensagem na área de texto.
     * Usa SwingUtilities.invokeLater() para garantir que a atualização ocorra
     * na thread de interface gráfica (EDT - Event Dispatch Thread).
     */
    private void appendMsg(String msg){
        SwingUtilities.invokeLater(() -> {
            areaMensagens.append(msg + "\n");           //Adiciona mensagem com quebra de linha

            //Faz o scroll automático para o fim da área de texto
            areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
        });
    }

    //Método executado pela thread de leitura. Fica em loop aguardando mensagens do servidor
    @Override
    public void run() {
        String msg;
        // Lê as mensagens enquanto a conexão estiver ativa
        while ((msg = clientSocket.getMessage()) != null) {
            appendMsg("-> " + msg); // Exibe no histórico
        }
        appendMsg("Conexão encerrada."); // Mostra aviso de desconexão
    }

    //Fecha o socket
    private void fecharConexao() {
        if (clientSocket != null) {
            clientSocket.close();
        }
    }

    public static void main(String[] args) {
        //Garante que a GUI seja criada na thread de interface gráfica (EDT)
        SwingUtilities.invokeLater(() -> new ClienteBatepapoGUI().iniciar());
    }
}
