import javax.swing.*; //Importação de bibloteca de entrada de dados

public class ex_4 {
    public static void main(String[] args){
            int dia,mes,ano; // Declaração das variaveis do tipo int
            // Entrada de dados do usuario
            dia = Integer.parseInt(JOptionPane.showInputDialog("Digite o dia:"));
            mes = Integer.parseInt(JOptionPane.showInputDialog("Digite o mes:"));
            ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano:"));
            // Exibe a Saida da variavel
            System.out.println(dia+"/"+mes+"/"+ano);
    }
}
