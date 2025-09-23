import javax.swing.JOptionPane;

public class Ex2 {
    public static void main(String[] args){
        double base = Double.parseDouble(JOptionPane.showInputDialog("Digite a base do retangulo: "));
        double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite a altura do retangulo: "));
        double area = (base*altura);
        JOptionPane.showMessageDialog(null,"A area do retangulo: "+area);
    }
}
