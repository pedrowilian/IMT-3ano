package Ex3;

public class ContaCorrente {
    private int numero;
    private int digito;
    private double saldo;
    private Agencia agencia;

    public ContaCorrente(int numero, Agencia agencia) {
        setNumeroEDigito(numero);
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumeroEDigito(int numero) {
        if (numero < 0 || numero > 9999){
            System.out.println("Número inválido");
            return;
        }

        this.numero = numero;

        int soma = 0;

        soma += numero / 1000 * 4;
        numero %= 1000;

        soma += numero / 100 * 6;
        numero %= 100;

        soma += numero / 10 * 8;
        numero %= 10;

        soma += numero * 2;

        if (soma == 10){
            this.digito = 0;
        }
        else {
            this.digito = soma % 11;
        }
    }

    public int getDigito() {
        return digito;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void depositar(double valor){
        saldo += valor;
    }

    public int sacar(double valor){
        if (valor < saldo){
            saldo -= valor;
            return 1;
        }
        else{
            return 0;
        }
    }

    public String imprimirSaldo(){
        String resp = "";
        resp += "Número da conta: " + this.numero + "-" + this.digito + "\n";
        resp += "Saldo: " + this.saldo + "\n";
        return resp;
    }

}
