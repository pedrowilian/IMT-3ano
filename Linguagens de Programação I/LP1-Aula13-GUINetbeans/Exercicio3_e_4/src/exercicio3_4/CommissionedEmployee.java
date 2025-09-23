package exercicio3_4;

public class CommissionedEmployee extends MonthlyEmployee {
    private final double commission;

    CommissionedEmployee(String name, String surname, String cpf, double salary, double commission)
    {
        super(name, surname, cpf, salary);
        this.commission = commission;
    }

    CommissionedEmployee()
    {
        super();
        this.commission = 0;
    }


    @Override
    public double salary()
    {
        return super.salary() + commission;
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.salary()), "Comissionado"};
        return data;
    }

}
