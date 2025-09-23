import javax.swing.JOptionPane; //Importa a Class JOptionPane para Entrada de dados

public class ex_1{
        public static void main(String[] args){

            String txt1,txt2,txt3; // Declaração das variaveis usadas
            // Busca a entrada das 3 strings
            txt1 = JOptionPane.showInputDialog("Digite uma palavra:");
            txt2 = JOptionPane.showInputDialog("Digite uma palavra:");
            txt3 = JOptionPane.showInputDialog("Digite uma palavra:");
            // Exibe a Saida das strings divide por espaços
            System.out.println(txt1+" "+txt2+" "+txt3);

    }
}