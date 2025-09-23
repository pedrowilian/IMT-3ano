import javax.swing.JOptionPane;
public class Ex1{
    public static void main(String[] args){
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a sua idade: "));
        int dias = idade*365;
        JOptionPane.showMessageDialog(null,"Voce esta vivo a: "+dias + " dias");
    }
}