package Ex3e4;

public class PessoaFisica {
    private String nome;
    private String sobrenome;
    private String cpf;

    public PessoaFisica(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public PessoaFisica() {
        this.nome = "Padrão";
        this.sobrenome = "Usuário";
        this.cpf = "12345678910";
    }

    public String dados() {
        return this.nome + " " + this.sobrenome + " - " + this.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] listarDados() {
        String[] dados = {this.nome, this.sobrenome, this.cpf};
        return dados;
    }
}
