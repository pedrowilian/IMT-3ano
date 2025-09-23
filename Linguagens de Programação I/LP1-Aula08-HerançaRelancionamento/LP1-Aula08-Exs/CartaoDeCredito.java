public class CartaoDeCredito extends Pagamento {
    private String numero_cartao;

    public CartaoDeCredito(String nome, String cpf, double valorAserPago, String numero_cartao) {
        super(nome, cpf, valorAserPago);
        this.numero_cartao = numero_cartao;
    }

    public String getNumeroCartao() {
        return numero_cartao;
    }

    public void setNumeroCartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }


}
