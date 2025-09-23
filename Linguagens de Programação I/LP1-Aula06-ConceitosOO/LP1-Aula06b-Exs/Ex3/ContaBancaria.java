public class ContaBancaria {
    // Atributos
    private String numeroConta;
    private String nomeTitular;
    private double saldo;

    // Construtor Padrão
    public ContaBancaria() {
        numeroConta = "0000-0";
        nomeTitular = "Nome do Titular";
        saldo = 0.0;
    }
    // Construtor Parametrizado
    public ContaBancaria(String nu, String no, double saldoInicial) {
        numeroConta = nu;
        nomeTitular = no;
        saldo = saldoInicial;
    }

    // Getters
    public String getNumeroConta() {
        return numeroConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setters
    public void setNumeroConta(String nu) {
        numeroConta = nu;
    }

    public void setNomeTitular(String no) {
        nomeTitular = no;
    }

    // Métodos de operação
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        } else
            return false;
    }
}
