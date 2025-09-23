import javax.swing.JOptionPane;

public class TesteProduto {
        public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome: ");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preco: "));
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de produto: "));
        Produto produto = new Produto(nome,preco,quantidade);
        String msg = "Nome: " + produto.getNome() + "\nPreco: "+ produto.getPreco() + 
        "\nQuantidade de produto: " + produto.getQuantidade();
        JOptionPane.showMessageDialog(null,msg);
    }
}
