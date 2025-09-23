
import javax.swing.JOptionPane;

public class Professor {
    private String nome;
    private int idade;

    public Professor(String n, int i){
        this.nome = n;
        this.idade = i;
    }
    //métodos modificadores
    public void setNome(String n){
        nome = n;
    }
    public void setIdade(int i){
        idade=i;
    }
    //métodos de acesso
    public String getNome(){
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public String getDados(){
        return "Nome do professor: "+ nome+"\n"+
               "Idade: "+idade+"\n";
    }

    public static void main(String[] args) throws Exception {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome: ", "Nome", JOptionPane.QUESTION_MESSAGE);
        int idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a idade: ", "Idade", JOptionPane.QUESTION_MESSAGE));

        Professor professor = new Professor(nome, idade);

        JOptionPane.showMessageDialog(null, professor.getDados(), "Dados", JOptionPane.INFORMATION_MESSAGE);
    
    }
}
