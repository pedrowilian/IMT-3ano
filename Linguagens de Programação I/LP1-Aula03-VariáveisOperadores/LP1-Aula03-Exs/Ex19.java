import javax.swing.JOptionPane;

public class Ex19 {
    public static void main(String[] args){
    double diagmaior = Double.parseDouble(JOptionPane.showInputDialog("Digite a diagonal maior do losango: "));
    double diagmenor = Double.parseDouble(JOptionPane.showInputDialog("Digite a diagonal menor do losango: "));
    double area = (diagmaior*diagmenor)/2;
    JOptionPane.showMessageDialog(null,"A area do losango: "+area);
    } 
}
