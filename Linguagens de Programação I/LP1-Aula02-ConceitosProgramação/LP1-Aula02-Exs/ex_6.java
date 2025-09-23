import javax.swing.*; //Importação de bibloteca de entrada de dados

public class ex_6 {
    public static void main(String[] args){
            char c; // Declaração das variaveis do tipo char e string
            String str,palavra="";
            // Entrada de dados do usuario
            for (int j=1; j<=10; j++){
                str = JOptionPane.showInputDialog("Digite o caracter "+j);
                c = str.charAt(0);
                palavra += c;
            }
            // Exibe a Saida da variavele
            System.out.println(palavra);
    }
}
