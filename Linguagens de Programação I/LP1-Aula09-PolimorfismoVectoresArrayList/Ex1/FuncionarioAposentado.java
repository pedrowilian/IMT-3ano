package Ex1;

public class FuncionarioAposentado extends Pessoa {
    double salarioAposentadoria;
    public FuncionarioAposentado(String name, int age, String role, double salarioAposentadoria) {
        super(name, age, role);
        this.salarioAposentadoria = salarioAposentadoria;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSalário aposentadoria: " + this.salarioAposentadoria;
    }
}
