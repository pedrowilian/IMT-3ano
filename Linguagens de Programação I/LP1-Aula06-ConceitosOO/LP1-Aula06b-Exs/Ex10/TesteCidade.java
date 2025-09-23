import javax.swing.JOptionPane;

public class TesteCidade {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome da cidade:");
        String estado = JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP, RJ):");
        int populacao = Integer.parseInt(JOptionPane.showInputDialog("Digite a população da cidade:"));

        Cidade cidade = new Cidade(nome, estado, populacao);

        String mensagem = "A cidade de " + cidade.getNome() + ", localizada em " + cidade.getEstado() +
                          ", possui " + cidade.getPopulacao() + " habitantes.";

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
