package Ex3e4;

public class EmpregadoComissionado extends EmpregadoMensalista {

    private double comissao;

    public EmpregadoComissionado(String nome, String sobrenome, String cpf, double salario, double comissao) {
        super(nome, sobrenome, cpf, salario);
        this.comissao = comissao;
    }

    public EmpregadoComissionado() {
        super();
        this.comissao = 0;
    }

    public double salario() {
        return super.salario() + comissao;
    }

    @Override
    public String[] listarDados() {
        String[] dados = {
            super.getNome(),
            super.getSobrenome(),
            super.getCpf(),
            String.valueOf(this.salario()),
            "Comissionado"
        };
        return dados;
    }
}

