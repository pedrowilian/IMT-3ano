import javax.swing.JOptionPane;
public class TesteTurma {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome: ");
        String curso = JOptionPane.showInputDialog("Curso: ");
        int quantidadeDeAlunos = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de alunos: "));
        int serie = Integer.parseInt(JOptionPane.showInputDialog("Serie: "));
        Turma turma = new Turma(nome,curso,quantidadeDeAlunos,serie);
        String msg = "Nome: " + turma.getNome() + "\nCurso: "+ turma.getCurso() + 
        "\nQuantidade de alunos: " + turma.getQuantidadeDeAlunos() + "\nSerie: " + turma.getSerie();
        JOptionPane.showMessageDialog(null,msg);
        quantidadeDeAlunos = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de alunos: "));
        turma.setQuantidadeDeAlunos(quantidadeDeAlunos);
        msg = "Nome: " + turma.getNome() + "\nCurso: "+ turma.getCurso() + 
        "\nQuantidade de alunos: " + turma.getQuantidadeDeAlunos() + "\nSerie: " + turma.getSerie();
        JOptionPane.showMessageDialog(null,msg);
    }

}
