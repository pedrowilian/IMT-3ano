package gui;

import dao.LoginDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btnLogin;
    private JButton btnVoltar;
    private LoginDAO loginDAO;

    public TelaLogin() {
        super("Login IMT");
        loginDAO = new LoginDAO();

        // Criar painel principal com GridLayout e espaçamento entre componentes
        JPanel painelPrincipal = new JPanel(new GridLayout(4, 2, 5, 5));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 0, 0)); 

        painelPrincipal.add(new JLabel("Usuário:"));
        tfUsuario = new JTextField();
        painelPrincipal.add(tfUsuario);

        painelPrincipal.add(new JLabel("Senha:"));
        pfSenha = new JPasswordField();
        painelPrincipal.add(pfSenha);

        btnLogin = new JButton("Entrar");
        btnVoltar = new JButton("Voltar");

        painelPrincipal.add(btnVoltar);
        painelPrincipal.add(btnLogin);

        add(painelPrincipal); 

        // Ação do botão "Entrar"
        btnLogin.addActionListener(e -> {
            String user = tfUsuario.getText().trim();
            String pass = new String(pfSenha.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha usuário e senha!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (loginDAO.validar(user, pass)) {
                new TelaNotas(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true); // <-- Ajuste se a tela inicial tiver outro nome
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 160);
        setLocationRelativeTo(null);
    }
}
