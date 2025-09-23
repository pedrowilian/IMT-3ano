import javax.swing.*; //Importação de bibloteca de entrada de dados

public class ex_7 {
    public static void main(String[] args){
            // Declaracao das variaveis usadas
            int milhar,centena,dezena,unidade;
            char um,dez,cem,mil;
            String numero;
            // Entrada de dados do usuario
            milhar = Integer.parseInt(JOptionPane.showInputDialog("Digite o milhar:"));
            centena = Integer.parseInt(JOptionPane.showInputDialog("Digite o centena:"));
            dezena = Integer.parseInt(JOptionPane.showInputDialog("Digite o dezena:"));
            unidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o unidade:"));
            um = (char) (unidade + '0');
            dez = (char) (dezena + '0');
            cem = (char) (centena + '0');
            mil = (char) (milhar + '0');
            numero = "" + mil + cem + dez + um;
            int num = Integer.parseInt(numero);
            // Exibe a Saida da variavel
            System.out.println("O número formado é: " + num);
    }
}
