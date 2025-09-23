public class Mensalista extends Empregado {
    private final double salario;

    public Mensalista(String nome, String sobrenome, String cpf, double salario) {
        super(nome, sobrenome, cpf);
        this.salario = salario;
    }

    public Mensalista() {
        super();
        this.salario = 0.0;
    }
    @Override 
    public double salario(){
        return this.salario;
    }
}
