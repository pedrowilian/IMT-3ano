public class Pagamento {
    private String nome;
    private String cpf;
    private double valorAserPago;

    public Pagamento(String nome, String cpf, double valorAserPago)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.valorAserPago = valorAserPago;
    }

    public double getValorAserPago() {
        return valorAserPago;
    }

    public void setValorAserPago(double valorAserPago) {
        this.valorAserPago = valorAserPago;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
