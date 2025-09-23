package exercicio3_4;

public class TaskEmployee extends Employed {
    private double tasks;
    private double valuePerTask;
    private double salary;

    public TaskEmployee(String name, String surname, String cpf, double tasks, double valuePerTask) {
        super(name, surname, cpf);
        this.tasks = tasks;
        this.valuePerTask = valuePerTask;
        this.salary = tasks * valuePerTask;
    }

    public TaskEmployee() {
        super();
        this.tasks = 0.0;
        this.valuePerTask = 0.0;
        this.salary = 0;
    }

    public double salary() {
        return this.salary;
    }


    public double getValuePerTask() {
        return valuePerTask;
    }

    public void setValuePerTask(double valuePerTask) {
        this.valuePerTask = valuePerTask;
    }

    public double getTasks() {
        return tasks;
    }

    public void setTasks(double tasks) {
        this.tasks = tasks;
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.salary()), "Tarefeiro"};
        return data;
    }
}
