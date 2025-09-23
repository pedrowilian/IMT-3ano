public class Cheque extends Pagamento {
    private String numero_cheque;

    public Cheque(String nome, String cpf, double valorAserPago, String numero_cheque) {
        super(nome, cpf, valorAserPago);
        this.numero_cheque = numero_cheque;
    }

    public String getCheckNumber() {
        return numero_cheque;
    }

    public void setCheckNumber(String numero_cheque) {
        this.numero_cheque = numero_cheque;
    }
}
