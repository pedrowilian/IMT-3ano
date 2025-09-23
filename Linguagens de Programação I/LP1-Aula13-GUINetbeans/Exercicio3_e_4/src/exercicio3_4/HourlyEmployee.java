package exercicio3_4;

public class HourlyEmployee extends Employed {
    private double hours;
    private double valuePerHour;
    private final double salary;

    public HourlyEmployee(String name, String surname, String cpf, double hours, double valuePerHour) {
        super(name, surname, cpf);
        this.hours = hours;
        this.valuePerHour = valuePerHour;
        this.salary = hours * valuePerHour;
    }

    public HourlyEmployee() {
        super();
        this.hours = 0.0;
        this.valuePerHour = 0.0;
        this.salary = 0;
    }

    @Override
    public double salary() {
        return this.salary;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getValuePerHour() {
        return valuePerHour;
    }

    public void setValuePerHour(double valuePerHour) {
        this.valuePerHour = valuePerHour;
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.salary()), "Horista"};
        return data;
    }

}
