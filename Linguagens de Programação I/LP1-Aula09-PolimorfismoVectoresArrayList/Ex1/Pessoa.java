package Ex1;

public class Pessoa {

    private String nome;
    private int idade;
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    Pessoa(String nome, int idade, String cargo) {
        this.nome = nome;
        this.idade = idade;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nIdade: " + this.idade + "\nCargo: " + this.cargo;

    }
}
