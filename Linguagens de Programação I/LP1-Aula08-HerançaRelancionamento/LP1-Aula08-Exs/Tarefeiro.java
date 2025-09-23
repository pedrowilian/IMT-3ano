public class Tarefeiro extends Empregado {
    private int tarefas;
    private double valor_por_tarefa;
    private final double salario;

    public Tarefeiro(String nome, String sobrenome, String cpf, int tarefas, double valor_por_tarefa) {
        super(nome, sobrenome, cpf);
        this.tarefas = tarefas;
        this.valor_por_tarefa = valor_por_tarefa;
        this.salario = tarefas * valor_por_tarefa;
    }

    public Tarefeiro() {
        super();
        this.tarefas = 0;
        this.valor_por_tarefa = 0.0;
        this.salario = 0;
    }

    public double getSalario() {
        return this.salario;
    }


    public double getValorPorTarefa() {
        return valor_por_tarefa;
    }

    public void setValorPorTarefa(double valor_por_tarefa) {
        this.valor_por_tarefa = valor_por_tarefa;
    }

    public double getTarefas() {
        return tarefas;
    }

    public void setTarefas(int tarefas) {
        this.tarefas = tarefas;
    }
}
