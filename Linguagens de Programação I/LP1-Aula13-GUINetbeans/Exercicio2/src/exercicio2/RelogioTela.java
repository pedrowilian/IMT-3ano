package exercicio2;

import javax.swing.*;               

public class RelogioTela extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RelogioTela.class.getName());

    public RelogioTela() {
        super("Relógio");
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    Relogio relogio = new Relogio();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        texto = new javax.swing.JLabel();
        CampoMostrador = new javax.swing.JTextField();
        ticTacbutton = new javax.swing.JButton();
        Horabutton = new javax.swing.JButton();
        Minutobutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        texto.setText("Relógio");

        CampoMostrador.setEditable(false);
        CampoMostrador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CampoMostrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoMostradorActionPerformed(evt);
            }
        });

        ticTacbutton.setText("TicTac");
        ticTacbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticTacbuttonActionPerformed(evt);
            }
        });

        Horabutton.setText("Hora");
        Horabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorabuttonActionPerformed(evt);
            }
        });

        Minutobutton.setText("Minuto");
        Minutobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinutobuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(CampoMostrador)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ticTacbutton)
                        .addGap(10, 10, 10)
                        .addComponent(Horabutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Minutobutton)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoMostrador, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticTacbutton)
                    .addComponent(Horabutton)
                    .addComponent(Minutobutton))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticTacbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticTacbuttonActionPerformed
        relogio.ticTac();
        CampoMostrador.setText(relogio.mostra());
    }//GEN-LAST:event_ticTacbuttonActionPerformed

    private void HorabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorabuttonActionPerformed
        String s = JOptionPane.showInputDialog( 
                this,                                              // Janela principal
                "Hora (0–23):",                                    // Mensagem para o usuário
                relogio.hora.getValor()                            // Valor atual como sugestão inicial
            );
        
        if (s != null && s.matches("\\d+")) {
                int h = Integer.parseInt(s);                       // Converte texto para inteiro

                // Verifica se está dentro do intervalo permitido
                if (h >= 0 && h <= 23) {
                    relogio.hora.setValor(h);
                    relogio.atualizaMostrador();
                    CampoMostrador.setText(relogio.mostra());
                } else {
                    // Mostra uma mensagem de erro se o valor for inválido
                    JOptionPane.showMessageDialog(
                        this,
                        "Digite um número entre 0 e 23.",
                        "Valor inválido",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                // Mostra uma mensagem de erro se não for número ou cancelar
                JOptionPane.showMessageDialog(
                    this,
                    "Entrada inválida. Use apenas números.",
                    "Valor inválido",
                    JOptionPane.WARNING_MESSAGE
                );
            }
    }//GEN-LAST:event_HorabuttonActionPerformed

    private void MinutobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinutobuttonActionPerformed
        String s = JOptionPane.showInputDialog(
                this,
                "Minuto (0–59):",
                relogio.minuto.getValor()
            );
        
        // Verifica se é número válido
            if (s != null && s.matches("\\d+")) {
                int m = Integer.parseInt(s);                       // Converte para inteiro

                if (m >= 0 && m <= 59) {
                    relogio.minuto.setValor(m); 
                    relogio.atualizaMostrador();
                    CampoMostrador.setText(relogio.mostra());
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Digite um número entre 0 e 59.",
                        "Valor inválido",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Entrada inválida. Use apenas números.",
                    "Valor inválido",
                    JOptionPane.WARNING_MESSAGE
                );
            }
    }//GEN-LAST:event_MinutobuttonActionPerformed

    private void CampoMostradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoMostradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoMostradorActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RelogioTela().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CampoMostrador;
    private javax.swing.JButton Horabutton;
    private javax.swing.JButton Minutobutton;
    private javax.swing.JLabel texto;
    private javax.swing.JButton ticTacbutton;
    // End of variables declaration//GEN-END:variables
}
