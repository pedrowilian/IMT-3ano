package Ex3e4;

public class EmpregadoHorista extends Empregado {
    private double horas;
    private double valorPorHora;
    private double salario;

    public EmpregadoHorista(String nome, String sobrenome, String cpf, double horas, double valorPorHora) {
        super(nome, sobrenome, cpf);
        this.horas = horas;
        this.valorPorHora = valorPorHora;
        this.salario = horas * valorPorHora;
    }

    public EmpregadoHorista() {
        super();
        this.horas = 0.0;
        this.valorPorHora = 0.0;
        this.salario = 0;
    }

    public double salario() {
        return this.salario;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    @Override
    public String[] listarDados() {
        String[] dados = {
            super.getNome(),
            super.getSobrenome(),
            super.getCpf(),
            String.valueOf(this.salario()),
            "Horista"
        };
        return dados;
    }
}
