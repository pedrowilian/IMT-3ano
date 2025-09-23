import javax.swing.JOptionPane;

public class TesteAtribuicao {
    public static void main(String[] args) {
        String nome_disc = JOptionPane.showInputDialog(null, "Digite o nome da disciplina: ", "Nome", JOptionPane.QUESTION_MESSAGE);
        boolean pratica = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "Digite se está praticando: [true/false]", "Prática", JOptionPane.QUESTION_MESSAGE));
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do professor: ", "Nome", JOptionPane.QUESTION_MESSAGE);
        int idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a idade do professor: ", "Idade", JOptionPane.QUESTION_MESSAGE));
        Disciplina disciplina = new Disciplina(nome_disc, pratica);
        Professor professor = new Professor(nome, idade);
        Atribuicao atribuicao = new Atribuicao(professor, disciplina);
        JOptionPane.showMessageDialog(null, atribuicao.getDados());
    }
}
