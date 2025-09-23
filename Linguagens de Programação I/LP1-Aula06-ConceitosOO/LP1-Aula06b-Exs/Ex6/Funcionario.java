public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    // Construtor Padrão
    public Funcionario() {
        nome = "Sem Nome";
        cargo = "Sem Cargo";
        salario = 0.0;
    }
    // Construtor Parametrizado
    public Funcionario(String n, String c, double s) {
        nome = n;
        cargo = c;
        salario = s;
    }

    // Métodos de acesso
    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    // Métodos modificadores
    public void setNome(String n) {
        nome = n;
    }

    public void setCargo(String c) {
        cargo = c;
    }

    public void setSalario(double s) {
        salario = s;
    }

    // Método que retorna o salário anual
    public double calcularSalarioAnual() {
        return salario * 12;
    }
}
