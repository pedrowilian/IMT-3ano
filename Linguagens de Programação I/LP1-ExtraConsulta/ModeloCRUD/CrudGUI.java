// ------------------------------
// 🔹 Tela Principal com CRUD
// ------------------------------

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class CrudGUI extends JFrame {
    private UsuarioDAO dao = new UsuarioDAO();
    private JTable tabela;
    private DefaultTableModel modelo;
    private JTextField txtNome, txtEmail, txtId;

    public CrudGUI() {
        setTitle("Sistema CRUD - Usuários");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --------------------------
        // Formulário
        // --------------------------
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Formulário"));

        panelForm.add(new JLabel("ID (para update/delete):"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);

        add(panelForm, BorderLayout.NORTH);

        // --------------------------
        // Botões CRUD
        // --------------------------
        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnCriar = new JButton("Criar");
        JButton btnLer = new JButton("Ler");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");
        panelBotoes.add(btnCriar);
        panelBotoes.add(btnLer);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnDeletar);

        add(panelBotoes, BorderLayout.SOUTH);

        // --------------------------
        // Tabela
        // --------------------------
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Email"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // --------------------------
        // Ações dos Botões
        // --------------------------
        btnCriar.addActionListener(e -> {
            dao.create(txtNome.getText(), txtEmail.getText());
            atualizarTabela();
            limparCampos();
        });

        btnLer.addActionListener(e -> atualizarTabela());

        btnAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                dao.update(id, txtNome.getText(), txtEmail.getText());
                atualizarTabela();
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!");
            }
        });

        btnDeletar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                dao.delete(id);
                atualizarTabela();
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!");
            }
        });
    }

    private void atualizarTabela() {
        modelo.setRowCount(0); // limpa
        for (Usuario u : dao.read()) {
            modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getEmail()});
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }
}
