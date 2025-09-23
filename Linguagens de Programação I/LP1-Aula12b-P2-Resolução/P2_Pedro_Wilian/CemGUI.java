import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
public class CemGUI extends JFrame {
    private JTextField txtNome, txtValorKwh, txtTestCode;
    private JTextField[] txtComodos, txtConsumos, txtCustos;
    private DatabaseConnection db;
    private static final String TEST_CODE = "ZLLCJ";

    public CemGUI() {
        db = new DatabaseConnection();
        initComponents();
    }

    private void initComponents() {
        setTitle("CEM - Consumo de Energia Mensal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpcao = new JMenu("Opção");
        menuOpcao.setMnemonic('O');
        JMenuItem itemSalvar = new JMenuItem("Salvar no B.D.", 'S');
        JMenuItem itemLer = new JMenuItem("Ler do B.D.", 'L');
        JMenuItem itemSair = new JMenuItem("Sair", 'A');
        menuOpcao.add(itemSalvar);
        menuOpcao.add(itemLer);
        menuOpcao.add(itemSair);
        menuBar.add(menuOpcao);
        setJMenuBar(menuBar);

        // Campos
        txtNome = new JTextField(20);
        txtValorKwh = new JTextField(10);
        txtTestCode = new JTextField(TEST_CODE, 10);
        txtTestCode.setEditable(false);
        txtTestCode.setBackground(Color.LIGHT_GRAY);
        txtComodos = new JTextField[3];
        txtConsumos = new JTextField[3];
        txtCustos = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            txtComodos[i] = new JTextField(15);
            txtConsumos[i] = new JTextField(10);
            txtCustos[i] = new JTextField(10);
            txtCustos[i].setEditable(false);
            txtCustos[i].setBackground(Color.LIGHT_GRAY);
            txtConsumos[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    calcularCustos();
                }
            });
        }
        txtValorKwh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calcularCustos();
            }
        });

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome do Cliente:"), gbc);
        gbc.gridx = 1; add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Valor do kWh (R$):"), gbc);
        gbc.gridx = 1; add(txtValorKwh, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 1;
        add(new JLabel("Test Code:"), gbc);
        gbc.gridx = 3; add(txtTestCode, gbc);
            // Ok
        for (int i = 0; i < 3; i++) {
            gbc.gridy = 4 + i; gbc.gridx = 0;
            add(new JLabel("Cômodo " + (i + 1)), gbc);
        }

        gbc.gridx = 1;
        gbc.gridy = 3; add(new JLabel("Cômodo:"), gbc);
        for (int i = 0; i < 3; i++) {
            gbc.gridy = 4 + i; add(txtComodos[i], gbc);
        }

        gbc.gridx = 2;
        gbc.gridy = 3; add(new JLabel("Consumo (kWh):"), gbc);
        for (int i = 0; i < 3; i++) {
            gbc.gridy = 4 + i; add(txtConsumos[i], gbc);
        }

        gbc.gridx = 3;
        gbc.gridy = 3; add(new JLabel("Custo (R$):"), gbc);
        for (int i = 0; i < 3; i++) {
            gbc.gridy = 4 + i; add(txtCustos[i], gbc);
        }

        // Ações do Menu
        itemSalvar.addActionListener(e -> salvarNoBanco());
        itemLer.addActionListener(e -> lerDoBanco());
        itemSair.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void calcularCustos() {
        try {
            double valorKwh = txtValorKwh.getText().trim().isEmpty() ? 0 :
Double.parseDouble(txtValorKwh.getText());
            for (int i = 0; i < 3; i++) {
                double consumo = txtConsumos[i].getText().trim().isEmpty() ? 0 :
Double.parseDouble(txtConsumos[i].getText());
                double custo = valorKwh * consumo;
                txtCustos[i].setText(String.format("%.2f", custo));
            }
        } catch (NumberFormatException ex) {
        }
    }

    private void salvarNoBanco() {
        try {
            Cliente cliente = new Cliente(txtNome.getText(), Double.parseDouble(txtValorKwh.getText()));
            for (int i = 0; i < 3; i++) {
                cliente.adicionarComodo(i, new Comodo(txtComodos[i].getText(),
Double.parseDouble(txtConsumos[i].getText())));
            }
            db.salvarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void lerDoBanco() {
        try {
            Cliente cliente = db.lerCliente(txtNome.getText());
            if (cliente != null) {
                txtValorKwh.setText(String.valueOf(cliente.getValorKwh()));
                Comodo[] comodos = cliente.getComodos();
                for (int i = 0; i < 3; i++) {
                    if (comodos[i] != null) {
                        txtComodos[i].setText(comodos[i].getNome());
                        txtConsumos[i].setText(String.valueOf(comodos[i].getConsumo()));
                        txtCustos[i].setText(String.format("%.2f", comodos[i].getCusto()));
                    }
                }
                JOptionPane.showMessageDialog(this, "Dados lidos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CemGUI().setVisible(true));
    }
}

