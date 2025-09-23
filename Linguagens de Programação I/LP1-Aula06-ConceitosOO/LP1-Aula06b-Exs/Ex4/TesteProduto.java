import javax.swing.JOptionPane;

public class TesteProduto {
    public static void main(String[] args) {
        // Leitura dos dados do produto
        String nome = JOptionPane.showInputDialog("Nome do produto:");
        String codigo = JOptionPane.showInputDialog("Código do produto:");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do produto:"));

        // Criação do objeto Produto
        Produto produto = new Produto(nome, codigo, preco);

        // Exibição da "etiqueta"
        String etiqueta = "ETIQUETA DO PRODUTO\\n" + "Nome: " + produto.getNome() + "\n" +
                          "Código: " + produto.getCodigo() + "\n" +
                          String.format("Preço: R$ %.2f", produto.getPreco());

        JOptionPane.showMessageDialog(null, etiqueta);
    }
}
