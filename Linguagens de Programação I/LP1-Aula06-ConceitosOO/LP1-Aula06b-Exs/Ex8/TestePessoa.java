import javax.swing.JOptionPane;
public class TestePessoa {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade:"));
        double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite sua altura (em metros):"));

        Pessoa pessoa = new Pessoa(nome, idade, altura);

        String mensagem = "Bem-vindo(a), " + pessoa.getNome() + "!\n" +
                          "Idade: " + pessoa.getIdade() + " anos\n" +
                          "Altura: " + pessoa.getAltura() + "m";

        JOptionPane.showMessageDialog(null, mensagem);
    }

}
