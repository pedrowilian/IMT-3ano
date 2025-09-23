public class PessoaFisica {
    private String nome;
    private String sobrenome;
    private String cpf;

    public PessoaFisica(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public PessoaFisica()
    {
        this.nome = "Default";
        this.sobrenome = "User";
        this.cpf = "12345678910";
    }
    public String dados()
    {
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

    public void setSobrenome(String surname) {
        this.sobrenome = surname;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }
}
