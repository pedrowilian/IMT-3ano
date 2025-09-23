import javax.swing.JOptionPane;

public class Ex3 {
    public static void main(String[] args){
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero inteiro: "));
        double resposta = Math.pow(num,2);
        JOptionPane.showMessageDialog(null,"O numero ao quadrado: "+resposta);
    }
}
