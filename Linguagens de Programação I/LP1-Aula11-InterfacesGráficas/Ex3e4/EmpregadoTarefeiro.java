package Ex3e4;

public class EmpregadoTarefeiro extends Empregado {

    private double tarefas;
    private double valorPorTarefa;
    private double salario;

    public EmpregadoTarefeiro(String nome, String sobrenome, String cpf, double tarefas, double valorPorTarefa) {
        super(nome, sobrenome, cpf);
        this.tarefas = tarefas;
        this.valorPorTarefa = valorPorTarefa;
        this.salario = tarefas * valorPorTarefa;
    }

    public EmpregadoTarefeiro() {
        super();
        this.tarefas = 0.0;
        this.valorPorTarefa = 0.0;
        this.salario = 0;
    }

    public double salario() {
        return this.salario;
    }

    public double getValorPorTarefa() {
        return valorPorTarefa;
    }

    public void setValorPorTarefa(double valorPorTarefa) {
        this.valorPorTarefa = valorPorTarefa;
    }

    public double getTarefas() {
        return tarefas;
    }

    public void setTarefas(double tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public String[] listarDados() {
        String[] dados = {
            super.getNome(),
            super.getSobrenome(),
            super.getCpf(),
            String.valueOf(this.salario()),
            "Tarefeiro"
        };
        return dados;
    }
}
