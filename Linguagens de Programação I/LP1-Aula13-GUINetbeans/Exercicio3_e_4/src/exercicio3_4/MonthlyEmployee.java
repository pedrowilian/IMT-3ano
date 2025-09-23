package exercicio3_4;

public class MonthlyEmployee extends Employed {
    private final double  salary;

    public MonthlyEmployee(String name, String surname, String cpf, double salary) {
        super(name, surname, cpf);
        this.salary = salary;
    }

    public MonthlyEmployee() {
        super();
        this.salary = 0.0;
    }

    @Override
    public double salary()
    {
        return this.salary;
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.salary()), "Mensalista"};
        return data;
    }

}
