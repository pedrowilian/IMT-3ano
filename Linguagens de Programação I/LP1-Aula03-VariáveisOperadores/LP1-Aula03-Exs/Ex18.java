import javax.swing.JOptionPane;

public class Ex18 {
    public static void main(String[] args){
    double base = Double.parseDouble(JOptionPane.showInputDialog("Digite a base do triangulo: "));
    double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite a altura do triangulo: "));
    double area = (base*altura)/2;
    JOptionPane.showMessageDialog(null,"A area do traingulo: "+area);
    } 
}
