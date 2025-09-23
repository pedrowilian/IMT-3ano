// =========================================
// MODELO COMPLETO DE INTERFACE GRÁFICA EM JAVA
// =========================================
// Estrutura padronizada para criar GUIs rápidas com Swing.
// - Tela grande e centralizada.
// - Blocos prontos para comentar/descomentar.
// - Menu, botões, campos de texto, labels e até tabela.
// - Comentários explicando como integrar lógica de negócio.
// =========================================

import java.awt.*;
import javax.swing.*;

public class ModeloGUI extends JFrame {

    // ------------------------------
    // 🔹 Atributos principais
    // ------------------------------
    private JTextField txtCampo1, txtCampo2, txtCampo3; 
    private JButton btnOk, btnCancelar, btnExtra;
    private JLabel lblResultado;
    private JTable tabela; // Exemplo de tabela

    public ModeloGUI() {
        // ===========================
        // 🔹 Configuração inicial
        // ===========================
        setTitle("Modelo Dinâmico - GUI Rápida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(800, 600); // 🔹 Tela maior que o padrão
        setLocationRelativeTo(null); // Centraliza a tela

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===========================
        // 🔹 MENU (descomente se precisar)
        // ===========================
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemNovo = new JMenuItem("Novo");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");
        menuArquivo.add(itemNovo);
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);

        // Exemplo de ação no menu
        itemSair.addActionListener(e -> System.exit(0));

        setJMenuBar(menuBar);

        // ===========================
        // 🔹 CAMPOS DE TEXTO
        // ===========================
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Campo 1:"), gbc);
        gbc.gridx = 1;
        txtCampo1 = new JTextField(15);
        add(txtCampo1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Campo 2:"), gbc);
        gbc.gridx = 1;
        txtCampo2 = new JTextField(15);
        add(txtCampo2, gbc);

        // 🔹 Campo opcional (descomente se precisar de mais):
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Campo 3:"), gbc);
        gbc.gridx = 1;
        txtCampo3 = new JTextField(15);
        add(txtCampo3, gbc);

        // ===========================
        // 🔹 BOTÕES
        // ===========================
        gbc.gridx = 0; gbc.gridy = 3;
        btnOk = new JButton("OK");
        add(btnOk, gbc);

        gbc.gridx = 1;
        btnCancelar = new JButton("Cancelar");
        add(btnCancelar, gbc);

        // 🔹 Botão extra (pronto para duplicar e criar novos)
        gbc.gridx = 2;
        btnExtra = new JButton("Extra");
        add(btnExtra, gbc);

        // ===========================
        // 🔹 LABEL (para exibir resultados)
        // ===========================
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        lblResultado = new JLabel("Resultado aparecerá aqui...");
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblResultado, gbc);

        // ===========================
        // 🔹 TABELA (descomente se precisar exibir dados)
        // ===========================
        String[] colunas = {"Coluna 1", "Coluna 2", "Coluna 3"};
        String[][] dados = {
            {"A1", "B1", "C1"},
            {"A2", "B2", "C2"}
        };
        tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1; gbc.weighty = 1;
        add(scroll, gbc);

        // ===========================
        // 🔹 AÇÕES DOS BOTÕES
        // ===========================
        btnOk.addActionListener(e -> {
            // Exemplo: concatenar valores dos campos
            String texto = txtCampo1.getText() + " - " + txtCampo2.getText();
            lblResultado.setText("Você digitou: " + texto);

            // 👉 Aqui você pode integrar lógica de negócio:
            // Exemplo: chamar um método de outra classe
            // Cliente cliente = new Cliente(txtCampo1.getText(), txtCampo2.getText());
            // cliente.salvar();
        });

        btnCancelar.addActionListener(e -> {
            // Limpa os campos
            txtCampo1.setText("");
            txtCampo2.setText("");
            if (txtCampo3 != null) txtCampo3.setText("");
            lblResultado.setText("Cancelado!");
        });

        btnExtra.addActionListener(e -> {
            // Exemplo simples: popup
            JOptionPane.showMessageDialog(this, "Botão Extra clicado!");
        });
    }

    // ===========================
    // 🔹 MAIN - executa o programa
    // ===========================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModeloGUI().setVisible(true));
    }
}
// =========================================