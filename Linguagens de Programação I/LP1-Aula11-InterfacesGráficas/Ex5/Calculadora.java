package Ex5;

import java.awt.*;
import javax.swing.*;

public class Calculadora extends JFrame {
        Botoes botoes;
        Display display;

    Calculadora() {
        super("Calculadora");
        display = new Display();
        botoes = new Botoes(display);

        setLayout(new GridBagLayout());

        GridBagConstraints displayConstraints = new GridBagConstraints();

        displayConstraints.gridx = 0;
        displayConstraints.gridy = 0;
        displayConstraints.gridwidth = 4;
        displayConstraints.anchor = GridBagConstraints.CENTER;
        displayConstraints.fill = GridBagConstraints.HORIZONTAL;
        displayConstraints.insets = new Insets(10, 10, 10, 10);

        add(display, displayConstraints);

        GridBagConstraints botoesConstraints = new GridBagConstraints();
        botoesConstraints.gridx = 0;
        botoesConstraints.gridy = 1;
        botoesConstraints.gridwidth = 4;
        botoesConstraints.anchor = GridBagConstraints.CENTER;
        botoesConstraints.insets = new Insets(10, 10, 10, 10);
        add(botoes, botoesConstraints);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}
