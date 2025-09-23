package Ex3;

public class Agencia {
    private String nome;
    private int numero;
    private int digito;

    public Agencia(String nome, int numero) {
        this.nome = nome;
        setNumeroEDigito(numero);

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

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public int getDigito() {
        return digito;
    }
}
