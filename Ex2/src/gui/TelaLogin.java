package gui;

import dao.LoginDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class TelaLogin extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btnEntrar;
    private JButton btnVoltar;
    private LoginDAO loginDAO;

    public TelaLogin(ResourceBundle bundle) {
        super(bundle.getString("titulo.login"));
        loginDAO = new LoginDAO();

        // Painel principal com borda de espaçamento
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margem interna

        // Campo usuário
        panel.add(new JLabel(bundle.getString("label.usuario")));
        tfUsuario = new JTextField();
        panel.add(tfUsuario);

        // Campo senha
        panel.add(new JLabel(bundle.getString("label.senha")));
        pfSenha = new JPasswordField();
        panel.add(pfSenha);

        // Botões
        btnVoltar = new JButton(bundle.getString("botao.voltar"));
        btnEntrar = new JButton(bundle.getString("botao.entrar"));
        panel.add(btnVoltar);
        panel.add(btnEntrar);

        add(panel);

        // Ação botão entrar
        btnEntrar.addActionListener(e -> {
            String user = tfUsuario.getText().trim();
            String pass = new String(pfSenha.getPassword()).trim();

            if (loginDAO.validar(user, pass)) {
                JOptionPane.showMessageDialog(null, bundle.getString("mensagem.loginSucesso"),"Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Abre a tela de notas e fecha a tela de login
                new TelaNotas(user, bundle).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    bundle.getString("mensagem.erroLogin"),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Ação botão voltar
        btnVoltar.addActionListener(e -> {
            new TelaInicial(bundle).setVisible(true);
            dispose();
        });

        // Configurações da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null);
    }
}
