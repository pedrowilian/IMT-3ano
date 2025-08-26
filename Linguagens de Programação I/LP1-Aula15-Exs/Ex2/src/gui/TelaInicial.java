package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class TelaInicial extends JFrame {
    private JButton btnLogin;
    private JButton btnCadastro;

    public TelaInicial(ResourceBundle bundle) {
        super(bundle.getString("titulo.inicial"));

        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lbl = new JLabel(bundle.getString("label.inicial"), SwingConstants.CENTER);
        btnLogin = new JButton(bundle.getString("botao.entrar"));
        btnCadastro = new JButton(bundle.getString("botao.cadastrar"));

        add(lbl);
        add(btnLogin);
        add(btnCadastro);

        btnLogin.addActionListener(e -> {
            new TelaLogin(bundle).setVisible(true);
            dispose();
        });

        btnCadastro.addActionListener(e -> {
            new TelaCadastro(bundle).setVisible(true);
            dispose();
        });

        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
