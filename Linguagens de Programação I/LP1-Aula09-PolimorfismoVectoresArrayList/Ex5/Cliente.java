package Ex5;

public class Cliente {
    private String nome;
    private String fone;
    private int id;

    public Cliente(String nome, String fone, int id) {
        this.nome = nome;
        this.fone = fone;
        this.id = id;
    }

    public String getData() {
        return "Nome: " + nome + "\nTelefone: " + fone + "\nID: " + id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
