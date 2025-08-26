package gui;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        super("Bem-vindo ao Sistema IMT");

        setLayout(new GridLayout(2, 1, 10, 10));
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");

        add(btnEntrar);
        add(btnCadastrar);

        btnEntrar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        btnCadastrar.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
