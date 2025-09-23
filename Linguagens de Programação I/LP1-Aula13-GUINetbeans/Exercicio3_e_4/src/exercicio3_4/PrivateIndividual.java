package exercicio3_4;

public class PrivateIndividual {
    private String name;
    private String surname;
    private String cpf;

    public PrivateIndividual(String name, String surname, String cpf) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
    }

    public PrivateIndividual()
    {
        this.name = "Default";
        this.surname = "User";
        this.cpf = "12345678910";
    }

    public String data()
    {
        return this.name + " " + this.surname + " - " + this.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] listData()
    {
        String[] data = {this.name, this.surname, this.cpf};
        return data;
    }
}
