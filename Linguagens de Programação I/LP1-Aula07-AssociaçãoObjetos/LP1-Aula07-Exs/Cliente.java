public class Cliente {
    private String nome;
    private String cpf;
    private ContaCorrente conta;

    public Cliente(String n, String c, ContaCorrente con){
        nome = n;
        cpf = c;
        conta = con;
    }
    //métodos modificadores
    public void setNome(String n){
        nome = n;
    }
    public void setCpf(String c){
        cpf = c;
    }
    public void setConta(ContaCorrente con){
        conta = con;
    }
    //métodos de acesso
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public ContaCorrente getConta(){
        return conta;
    }

}
