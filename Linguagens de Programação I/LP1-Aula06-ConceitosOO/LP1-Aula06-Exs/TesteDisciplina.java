import javax.swing.JOptionPane;

public class TesteDisciplina {
       public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Nome: ");
        String professor = JOptionPane.showInputDialog("Professor: ");
        int semestre = Integer.parseInt(JOptionPane.showInputDialog("Semestre: "));
        boolean ofertada = Boolean.parseBoolean(JOptionPane.showInputDialog("Ofertada (true/false): "));
        Disciplina disciplina = new Disciplina(nome,professor,semestre,ofertada);
        String msg = "Nome: " + disciplina.getNome() + "\nProfessor: "+ disciplina.getProfessor() + 
        "\nSemestre: " + disciplina.getSemestre();
        if (disciplina.getOfertada())
            msg += "\nOfertada: Sim";
        else 
            msg += "\nOfertada: Não";
        JOptionPane.showMessageDialog(null,msg);
    }
}
