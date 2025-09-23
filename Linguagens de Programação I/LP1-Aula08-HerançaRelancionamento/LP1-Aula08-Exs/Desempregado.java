public class Desempregado extends PessoaFisica {

    private double seguroDesemprego;
    public Desempregado(String nome, String surname, String cpf, double seguroDesemprego) {
        super(nome, surname, cpf);
        this.seguroDesemprego = seguroDesemprego;
    }

    public Desempregado()
    {
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
    public String dados(){
        return super.dados() + " - " + "R$ " + this.seguroDesemprego;
    }
}
