package Ex3e4;

public class Desempregado extends PessoaFisica {

    private double seguroDesemprego;

    public Desempregado(String nome, String sobrenome, String cpf, double seguroDesemprego) {
        super(nome, sobrenome, cpf);
        this.seguroDesemprego = seguroDesemprego;
    }

    public Desempregado() {
        super();
        this.seguroDesemprego = 0.0;
    }

    public double getSeguroDesemprego() {
        return seguroDesemprego;
    }

    public void setSeguroDesemprego(double seguroDesemprego) {
        this.seguroDesemprego = seguroDesemprego;
    }

    @Override
    public String dados() {
        return super.dados() + " - R$ " + this.seguroDesemprego;
    }

    @Override
    public String[] listarDados() {
        String[] dados = {
            super.getNome(),
            super.getSobrenome(),
            super.getCpf(),
            String.valueOf(this.seguroDesemprego),
            "Desempregado"
        };
        return dados;
    }
}

