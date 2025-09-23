package Ex3e4;

public class EmpregadoMensalista extends Empregado {
    private double salario;

    public EmpregadoMensalista(String nome, String sobrenome, String cpf, double salario) {
        super(nome, sobrenome, cpf);
        this.salario = salario;
    }

    public EmpregadoMensalista() {
        super();
        this.salario = 0.0;
    }

    public double salario() {
        return this.salario;
    }

    @Override
    public String[] listarDados() {
        String[] dados = {
            super.getNome(),
            super.getSobrenome(),
            super.getCpf(),
            String.valueOf(this.salario()),
            "Mensalista"
        };
        return dados;
    }
}

