package gui;

import dao.LoginDAO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.ResourceBundle;

public class TelaCadastro extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JPasswordField pfConfirmaSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;
    private LoginDAO loginDAO;

    public TelaCadastro(ResourceBundle bundle) {
        super(bundle.getString("titulo.cadastro"));
        loginDAO = new LoginDAO();

        // Painel principal com margem
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20)); // top, left, bottom, right

        panel.add(new JLabel(bundle.getString("label.usuario")));
        tfUsuario = new JTextField();
        panel.add(tfUsuario);

        panel.add(new JLabel(bundle.getString("label.senha")));
        pfSenha = new JPasswordField();
        panel.add(pfSenha);

        panel.add(new JLabel(bundle.getString("label.confirmaSenha")));
        pfConfirmaSenha = new JPasswordField();
        panel.add(pfConfirmaSenha);

        btnVoltar = new JButton(bundle.getString("botao.voltar"));
        btnCadastrar = new JButton(bundle.getString("botao.cadastrar"));
        panel.add(btnVoltar);
        panel.add(btnCadastrar);

        add(panel, BorderLayout.CENTER);

        // Ações
        btnCadastrar.addActionListener(e -> {
            String user = tfUsuario.getText().trim();
            String pass = new String(pfSenha.getPassword()).trim();
            String confirma = new String(pfConfirmaSenha.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty() || confirma.isEmpty()) {
                JOptionPane.showMessageDialog(this, bundle.getString("mensagem.preencherCampos"), "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!pass.equals(confirma)) {
                JOptionPane.showMessageDialog(this, bundle.getString("mensagem.senhasNaoConferem"), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (loginDAO.usuarioExiste(user)) {
                JOptionPane.showMessageDialog(this, bundle.getString("mensagem.usuarioExiste"), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = loginDAO.cadastrarUsuario(user, pass);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, bundle.getString("mensagem.cadastroSucesso"),"Sucesso", JOptionPane.INFORMATION_MESSAGE);
                 // Volta para a tela de login
                new TelaLogin(bundle).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("mensagem.erroCadastro"), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVoltar.addActionListener(e -> {
            new TelaInicial(bundle).setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
    }
}
