import javax.swing.JOptionPane;

public class TesteFilme {
        public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome do filme:");
        String diretor = JOptionPane.showInputDialog("Diretor do filme:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano de lançamento:"));

        Filme filme = new Filme(nome, diretor, ano);

        String mensagem = "DADOS DO FILME\\n" + "Nome: " + filme.getNome() + "\n" +
                          "Diretor: " + filme.getDiretor() + "\n" +
                          "Ano de Lançamento: " + filme.getAnoDeLancamento();

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
