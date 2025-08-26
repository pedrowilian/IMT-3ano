package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class TelaIdioma extends JFrame {

    public TelaIdioma() {
        super("Seleção de Idioma");

        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lbl = new JLabel("Selecione o idioma / Select language:", SwingConstants.CENTER);
        JButton btnPt = new JButton("Português");
        JButton btnEn = new JButton("English");

        add(lbl);
        add(btnPt);
        add(btnEn);

        btnPt.addActionListener(e -> abrirTelaInicial(Locale.of("pt", "BR")));
        btnEn.addActionListener(e -> abrirTelaInicial(Locale.of("en", "US")));

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void abrirTelaInicial(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ex2",locale);
        new TelaInicial(bundle).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaIdioma().setVisible(true));
    }
}

