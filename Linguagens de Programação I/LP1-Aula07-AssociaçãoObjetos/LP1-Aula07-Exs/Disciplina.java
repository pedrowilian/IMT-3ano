import javax.swing.JOptionPane;

public class Disciplina {
    private String nome;
    private boolean pratica;

    public Disciplina(String n, boolean p){
        nome = n;
        pratica = p;
    }
    //métodos modificadores
    public void setNome(String n){
        nome = n;
    }
    public void setIPratica(boolean p){
        pratica=p;
    }
    //métodos de acesso
    public String getNome(){
        return nome;
    }
    public boolean getPratica(){
        return pratica;
    }
    public String getDados(){
        String saida = "Nome da disciplina: "+nome+"\nDisciplina Prática: ";
        if(pratica){
            saida+="sim";
        }
        else{
            saida+="não";
        }
        return saida;
    }

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da disciplina: ", "Nome", JOptionPane.QUESTION_MESSAGE);
        boolean pratica = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "Digite se está praticando: [true/false]", "Prática", JOptionPane.QUESTION_MESSAGE));

        Disciplina disciplina = new Disciplina(nome, pratica);
        
        //exibição
        JOptionPane.showMessageDialog(null, disciplina.getDados(), "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
    }
}
