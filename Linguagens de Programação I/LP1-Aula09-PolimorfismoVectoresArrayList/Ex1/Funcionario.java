package Ex1;

public class Funcionario extends Pessoa {
    private double salario;

    Funcionario(String nome, int idade, String cargo, double salario) {
        super(nome, idade, cargo);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double categoria() {
        if (this.getIdade() > 20)
        {
            return 30;
        }
        else {
            return 10;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nsalario: " + this.salario + "\nCategoria: " + this.categoria();
    }
}
