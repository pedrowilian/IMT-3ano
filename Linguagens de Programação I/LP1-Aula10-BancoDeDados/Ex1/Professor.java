package Ex1;

public class Professor {
    private String nome;
    private int matricula;
    private int idade;

    public Professor(){
        this.nome = "";
        this.matricula = 0;
        this.idade = 0;
    }

    Professor(int matricula){
        this.nome = "";
        this.matricula = matricula;
        this.idade = 0;
    }

    Professor(String nome, int matricula, int idade){
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
    }


    public String toString(){
        return "Nome: " + this.nome + "\nMatricula: " + this.matricula + "\nIdade: " + this.idade;
    }

    public String getNome(){
        return this.nome;
    }
    public int getIdade(){
        return this.idade;
    }
    public int getMatricula(){
        return this.matricula;
    }

    public void setMatricula(int matriculaProfessor) {
        this.matricula = matriculaProfessor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
