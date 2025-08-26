package gui;

import dao.LoginDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaCadastro extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JPasswordField pfConfirmaSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;
    private LoginDAO loginDAO;

    public TelaCadastro() {
        super("Cadastro de Usuário");
        loginDAO = new LoginDAO();

        // Painel principal com espaçamento entre campos
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10)); // 10px de espaçamento entre linhas/colunas
        painel.setBorder(new EmptyBorder(10, 10, 0, 0)); // margem interna (top, left, bottom, right)

        painel.add(new JLabel("Usuário:"));
        tfUsuario = new JTextField();
        painel.add(tfUsuario);

        painel.add(new JLabel("Senha:"));
        pfSenha = new JPasswordField();
        painel.add(pfSenha);

        painel.add(new JLabel("Confirmar Senha:"));
        pfConfirmaSenha = new JPasswordField();
        painel.add(pfConfirmaSenha);

        btnVoltar = new JButton("Voltar");
        btnCadastrar = new JButton("Cadastrar");
        painel.add(btnVoltar);
        painel.add(btnCadastrar);

        add(painel);

        // Ação do botão "Cadastrar"
        btnCadastrar.addActionListener(e -> {
            String user = tfUsuario.getText().trim();
            String pass = new String(pfSenha.getPassword()).trim();
            String confirma = new String(pfConfirmaSenha.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty() || confirma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!pass.equals(confirma)) {
                JOptionPane.showMessageDialog(this, "Senhas não conferem!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (loginDAO.usuarioExiste(user)) {
                JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = loginDAO.cadastrarUsuario(user, pass);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                new TelaLogin().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro no cadastro. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true); // Ajustar para sua tela inicial real
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
    }
}
