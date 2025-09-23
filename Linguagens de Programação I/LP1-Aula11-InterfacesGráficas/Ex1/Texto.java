package Ex1;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
// Class tem que usar JFrame para ser interface e ActionListener para ter acoes
public class Texto extends JFrame implements ActionListener{
    // Declara as coisas necessarias
    private JButton botao1,botao2,botao3; //botao
    //private JTextField texto; //campo texto de uma linha
    private JLabel etiqueta,texto; // Etiqueta
    public Texto(){
        // Construtor da superclasse e configurar o titulo
        super("Texto");
        // instanciar os widgets
        botao1 = new JButton("Mostrar");
        botao2 = new JButton("Limpar");
        botao3 = new JButton("Sair");
        texto = new JLabel("");
        etiqueta = new JLabel("Texto");
        // pega o painel
        Container caixa = getContentPane();
        // configura o gerenciador de layout
        caixa.setLayout(new BorderLayout());
        //cria os painéis secundários
        JPanel painelSul = new JPanel(new FlowLayout());
        JPanel painelCentro = new JPanel(new GridLayout(1,3));
        //adiciona os widgets nos painéis secundários
        painelSul.add(botao1);
        painelSul.add(botao2);
        painelSul.add(botao3);
        painelCentro.add(etiqueta);
        painelCentro.add(texto);
        //adiciona os painéis secundários no principal
        caixa.add(painelSul, BorderLayout.SOUTH);
        caixa.add(painelCentro, BorderLayout.CENTER);

        // Registra objeto como listener
        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao3.addActionListener(this);
        setSize(250,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==botao1){
            texto.setText("Evandro God");
        }
        else if(e.getSource()==botao2){
            texto.setText("");
        }
        else{
            System.exit(0);
        }
    }
}