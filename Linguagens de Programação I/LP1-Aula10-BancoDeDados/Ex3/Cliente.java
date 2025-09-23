package Ex3;

public class Cliente {
    private String nome;
    private String cpf;
    private ContaCorrente conta;

    Cliente(String nome, String cpf, ContaCorrente conta){
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public String toString(){
        String resp = "";
        resp += "Nome: " + nome + "\n";
        resp += "CPF: " + cpf + "\n";
        resp += conta.imprimirSaldo();
        return resp;

    }
}
