import javax.swing.JOptionPane;

public class Ex20 {
    public static void main(String[] args){
    double a = Double.parseDouble(JOptionPane.showInputDialog("Digite o lado 1 do triangulo: "));
    double b = Double.parseDouble(JOptionPane.showInputDialog("Digite o lado 2 do triangulo: "));
    double ang = Double.parseDouble(JOptionPane.showInputDialog("Digite o angulo em graus: "));
    double c = Math.sqrt(a*a+b*b-2*a*b*Math.cos(Math.toRadians(ang)));
    JOptionPane.showMessageDialog(null,"O terceiro lado do triangulo é: "+c);
    } 
}
