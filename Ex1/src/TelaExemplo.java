import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

public class TelaExemplo extends JFrame implements ActionListener {
    private JButton bt;
    private JTextField tx;
    private JLabel rt;
    private ResourceBundle bn = null;
    public TelaExemplo(){
        // Menu simples de escolha de idioma 
        int op = Integer.parseInt(JOptionPane.showInputDialog("Idioma - Language - Langue - Lingua\n \n1-Português\n2-Inglês\n3-Francês\n4-Italiano\n"));
        switch(op){
            case 1:
                bn = ResourceBundle.getBundle("ex1", new Locale("pt", "BR"));
                break;
            case 2:
                bn = ResourceBundle.getBundle("ex1", Locale.US);
                break;
            case 3:
                bn = ResourceBundle.getBundle("ex1", Locale.FRANCE);
                break;
            case 4:
                bn = ResourceBundle.getBundle("ex1", Locale.ITALY);
                break;
            default:
                bn = ResourceBundle.getBundle("ex1");
        }
        // Ecolhe Layout do conteiner
        Container cx = getContentPane();
        cx.setLayout(new FlowLayout());
        // Intanciacao dos objetos
        bt = new JButton(bn.getString("tela1.botao.calcular"));
        tx = new JTextField(10);
        rt = new JLabel(bn.getString("tela1.rotulo.valor")+":");
        // Adicao dos objetos ao conteiner
        cx.add(bt);
        cx.add(tx);
        cx.add(rt);
        bt.addActionListener(this);
        setTitle(bn.getString("tela1.titulo"));
        setSize(300, 150);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(tx.getText().length() == 0){
            JOptionPane.showMessageDialog(null, bn.getString("Mensagem.valor.nulo"), 
            bn.getString("tela1.erro.titulo"), JOptionPane.ERROR_MESSAGE);
        }else{
            int n = Integer.parseInt(tx.getText());
            n = n * n;
            tx.setText(""+n);
        }
    }

    public static void main(String[] args) throws Exception {
        TelaExemplo tela = new TelaExemplo();
    }
}
