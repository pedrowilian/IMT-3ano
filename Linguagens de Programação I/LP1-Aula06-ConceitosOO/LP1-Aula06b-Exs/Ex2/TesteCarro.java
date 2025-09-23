import javax.swing.JOptionPane;

public class TesteCarro {
    public static void main(String[] args) {
        // Entrada dos dados via JOptionPane
        String marca = JOptionPane.showInputDialog("Marca do carro:");
        String modelo = JOptionPane.showInputDialog("Modelo do carro:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano de fabricação:"));

        // Instanciando o objeto Carro
        Carro carro = new Carro(marca, modelo, ano);

        // Exibindo os dados
        String mensagem = "Marca: " + carro.getMarca() +
                          "\nModelo: " + carro.getModelo() +
                          "\nAno de fabricação: " + carro.getAnoDeFabricacao();

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
