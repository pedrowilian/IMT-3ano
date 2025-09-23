package exercicio3_4;

public class Employed extends PrivateIndividual {
    public Employed(String name, String surname, String cpf) {
        super(name, surname, cpf);
    }

    public Employed() {
        super();
    }

    public double salary() {
        return 0.0;
    }

    @Override
    public String data()
    {
        return super.data() + " - R$ " + this.salary();
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.salary()), "Empregado"};
        return data;
    }

}
