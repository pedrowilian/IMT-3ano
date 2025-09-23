import javax.swing.*; //Importação de bibloteca de entrada de dados

public class ex_5 {
    public static void main(String[] args){
            float peso,altura; // Declaração das variaveis do tipo float
            // Entrada de dados do usuario
            peso = Float.parseFloat(JOptionPane.showInputDialog("Digite seu peso(kg):"));
            altura = Float.parseFloat(JOptionPane.showInputDialog("Digite sua altura(m):"));
            // Exibe a Saida da variavel
            System.out.println(peso+"kg e "+altura+"m");
    }
}
