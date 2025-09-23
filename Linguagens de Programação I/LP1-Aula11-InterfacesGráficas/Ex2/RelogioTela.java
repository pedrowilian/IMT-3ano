package Ex2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RelogioTela extends JFrame implements ActionListener {

    private Relogio relogio;
    private JLabel label;
    private JButton ticTacbutton;
    private JButton horaButton;
    private JButton minutoButton;

    public RelogioTela(){
        relogio = new Relogio();

        setLayout(new GridBagLayout());

        // create constraints for the label
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.CENTER;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        label = new JLabel(relogio.mostra());
        label.setFont(new Font("Serif", Font.BOLD, 30));


        add(label, labelConstraints);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.anchor = GridBagConstraints.CENTER;
        buttonConstraints.insets = new Insets(5, 5, 5, 5);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        ticTacbutton = new JButton("Tic Tac");
        buttonPanel.add(ticTacbutton);
        ticTacbutton.addActionListener(this);


        horaButton = new JButton("Hora");
        buttonPanel.add(horaButton);
        horaButton.addActionListener(this);

        minutoButton = new JButton("Minuto");
        buttonPanel.add(minutoButton);
        minutoButton.addActionListener(this);

        buttonPanel.setPreferredSize(new Dimension(300, 50));

        add(buttonPanel, buttonConstraints);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ticTacbutton){
            relogio.ticTac();
            label.setText(relogio.mostra());
        }else if(e.getSource() == horaButton){
            int valor = Integer.parseInt(JOptionPane.showInputDialog("Hora: "));
            relogio.hora.atualizar(valor);
            relogio.atualizaMostrador();
            label.setText(relogio.mostra());
        }else if(e.getSource() == minutoButton){
            int valor = Integer.parseInt(JOptionPane.showInputDialog("Hora: "));
            relogio.minuto.atualizar(valor);
            relogio.atualizaMostrador();
            label.setText(relogio.mostra());
        }

    }
}

