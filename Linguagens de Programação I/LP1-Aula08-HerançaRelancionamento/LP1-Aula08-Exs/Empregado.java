public class Empregado extends PessoaFisica {
    public Empregado(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome, cpf);
    }

    public Empregado() {
        super();
    }
    
    public double salario() {
        return 0.0;
    }

    @Override
    public String dados()
    {
        return super.dados() + " - R$ " + this.salario();
    }

}
