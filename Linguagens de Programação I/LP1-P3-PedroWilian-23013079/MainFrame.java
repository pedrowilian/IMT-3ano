import java.awt.BorderLayout;
import java.io.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JTextArea textArea;
    private String message = "";
    private byte[] messageBytes;
    private int checkSum = 0;
    private CryptoRSA crypto;
    private ReadTextFile reader;
    private CreateTextFile writer;

    public MainFrame() {
        // Criação da GUI
        crypto = new CryptoRSA();
        reader = new ReadTextFile();
        writer = new CreateTextFile();
        setTitle("GUI - P3 - PedroWilian - 23013079 - MCJQP");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        // Menu "Mensagem"
        JMenu mensagemMenu = new JMenu("Mensagem");
        JMenuItem lerItem = new JMenuItem("Ler arquivo de mensagem");
        lerItem.addActionListener(e -> lerMensagem());
        mensagemMenu.add(lerItem);

        JMenuItem gerarCSItem = new JMenuItem("Gerar e mostrar CS");
        gerarCSItem.addActionListener(e -> gerarMostrarCS());
        mensagemMenu.add(gerarCSItem);

        JMenuItem salvarCSItem = new JMenuItem("Salvar arquivo de mensagem com CS");
        salvarCSItem.addActionListener(e -> salvarMensagemComCS());
        mensagemMenu.add(salvarCSItem);
        menuBar.add(mensagemMenu);

        // Menu "Chaves"
        JMenu chavesMenu = new JMenu("Chaves");
        JMenuItem gerarPubItem = new JMenuItem("Gerar e salvar chave pública");
        gerarPubItem.addActionListener(e -> gerarSalvarChavePublica());
        chavesMenu.add(gerarPubItem);

        JMenuItem gerarPrivItem = new JMenuItem("Gerar e salvar chave privada");
        gerarPrivItem.addActionListener(e -> gerarSalvarChavePrivada());
        chavesMenu.add(gerarPrivItem);
        menuBar.add(chavesMenu);

        // Menu "Criptografia"
        JMenu criptoMenu = new JMenu("Criptografia");
        JMenuItem cifrarItem = new JMenuItem("Cifrar e salvar a mensagem com CS cifrada");
        cifrarItem.addActionListener(e -> cifrarSalvar());
        criptoMenu.add(cifrarItem);

        JMenuItem decifrarItem = new JMenuItem("Decifrar e salvar a mensagem com CS decifrada");
        decifrarItem.addActionListener(e -> decifrarSalvar());
        criptoMenu.add(decifrarItem);
        menuBar.add(criptoMenu);

        // Menu "Geral"
        JMenu geralMenu = new JMenu("Geral");
        JMenuItem mostrarDecItem = new JMenuItem("Mostrar a mensagem com CS decifrada");
        mostrarDecItem.addActionListener(e -> mostrarDecifrada());
        geralMenu.add(mostrarDecItem);

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(e -> System.exit(0));
        geralMenu.add(sairItem);
        menuBar.add(geralMenu);

        setJMenuBar(menuBar);
    }

    // Ler Mensagem do msg_original
    private void lerMensagem() {
        try {
            reader.openFile("msg_original.txt");
            message = reader.readFile();
            reader.closeFile();
            messageBytes = message.getBytes("UTF-8");
            textArea.append("Mensagem lida com sucesso de msg_original.txt: " + message + "\n");
        } catch (Exception ex) {
            textArea.append("Erro ao ler mensagem: " + ex.getMessage() + "\n");
        }
    }

    // Gera um Checksum da msg_original
    private void gerarMostrarCS() {
        if (message.isEmpty()) {
            textArea.append("Primeiro leia a mensagem.\n");
            return;
        }
        checkSum = Checksum.calcularChecksum(message.toCharArray());
        String hex = Integer.toHexString(checkSum).toUpperCase();
        char asciiChar = (char) checkSum;
        textArea.append("CheckSum gerado: 0x" + hex + " (decimal: " + checkSum + ", ASCII: " + asciiChar + ")\n");
        textArea.append("Checksum: "+ message + asciiChar + "\n");
    }   

    // Salva o Checksum no msg_com_cs
    private void salvarMensagemComCS() {
        if (message.isEmpty() || checkSum == 0) {
            textArea.append("Primeiro leia a mensagem e gere o CS.\n");
            return;
        }
        try {
        char asciiChar = (char) checkSum;
            String fullMessage = ""+ message + asciiChar;
            writer.openFile("msg_com_cs.txt");
            writer.addText(fullMessage);
            writer.closeFile();
            textArea.append("Arquivo msg_com_cs.txt salvo com sucesso.\n");
        } catch (Exception ex) {
            textArea.append("Erro ao salvar mensagem com CS: " + ex.getMessage() + "\n");
        }
    }

    // Gera e Salva Chave Publica
    private void gerarSalvarChavePublica() {
        try {
            crypto.geraParDeChaves(new File("chave.publica"), new File("chave.privada"));
            textArea.append("Chave pública gerada e salva em chave.publica.\n");
        } catch (Exception ex) {
            textArea.append("Erro ao gerar/salvar chave pública: " + ex.getMessage() + "\n");
        }
    }
    // Gera e Salva Chave Privada
    private void gerarSalvarChavePrivada() {
        try {
            crypto.geraParDeChaves(new File("chave.publica"), new File("chave.privada"));
            textArea.append("Chave privada gerada e salva em chave.privada.\n");
        } catch (Exception ex) {
            textArea.append("Erro ao gerar/salvar chave privada: " + ex.getMessage() + "\n");
        }
    }
    // Cifrar a Menssagem e salvar
    private void cifrarSalvar() {
        try {
            reader.openFile("msg_com_cs.txt");
            String sMsgClara = reader.readFile();
            reader.closeFile();
            byte[] bMsgClara = sMsgClara.getBytes("ISO-8859-1");
            if (bMsgClara.length > 117) { // Limite para RSA 1024 bits com padding
                textArea.append("Erro: Dados muito grandes para criptografia RSA (máx. 117 bytes).\n");
                return;
            }
            crypto.geraCifra(bMsgClara, new File("chave.publica"));
            byte[] bMsgCifrada = crypto.getTextoCifrado();
            FileOutputStream fos = new FileOutputStream("msg_cifrada.txt");
            fos.write(bMsgCifrada);
            fos.close();
            textArea.append("Mensagem cifrada e salva em msg_cifrada.txt.\n");
        } catch (Exception ex) {
            textArea.append("Erro ao cifrar: " + ex.getMessage() + "\n");
        }
    }
    // Decifrar e salvar
    private void decifrarSalvar() {
        try {
            FileInputStream fis = new FileInputStream("msg_cifrada.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            fis.close();
            byte[] bMsgCifrada = baos.toByteArray();
            crypto.geraDecifra(bMsgCifrada, new File("chave.privada"));
            byte[] bMsgDecifrada = crypto.getTextoDecifrado();
            String sMsgDecifrada = new String(bMsgDecifrada, "ISO-8859-1");
            writer.openFile("msg_decifrada.txt");
            writer.addText(sMsgDecifrada);
            writer.closeFile();
            textArea.append("Mensagem decifrada e salva em msg_decifrada.txt.\n");
        } catch (Exception ex) {
            textArea.append("Erro ao decifrar: " + ex.getMessage() + "\n");
        }
    }
    // Mostrar a mensagem decifrada
    private void mostrarDecifrada() {
        try {
            reader.openFile("msg_decifrada.txt");
            String content = reader.readFile();
            reader.closeFile();
            textArea.append("Mensagem com CS decifrada:\n" + content + "\n");
        } catch (Exception ex) {
            textArea.append("Erro ao mostrar decifrada: " + ex.getMessage() + "\n");
        }
    }
}