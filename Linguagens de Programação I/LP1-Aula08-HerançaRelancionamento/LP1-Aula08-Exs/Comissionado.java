public class Comissionado extends Mensalista {
    private final double comissao;

    public Comissionado(String nome, String sobrenome, String cpf, double salario, double comissao){
        super(nome, sobrenome, cpf, comissao);
        this.comissao = comissao;
    }
    public Comissionado(){
        super();
        this.comissao = 0;
    }

    @Override
    public double salario(){
        return super.salario() + comissao;
    }

}
