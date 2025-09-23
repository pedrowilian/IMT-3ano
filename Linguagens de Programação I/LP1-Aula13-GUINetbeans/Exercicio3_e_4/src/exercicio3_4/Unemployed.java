package exercicio3_4;

public class Unemployed extends PrivateIndividual {

    private double unemploymentInsurance;
    public Unemployed(String name, String surname, String cpf, double unemploymentInsurance) {
        super(name, surname, cpf);
        this.unemploymentInsurance = unemploymentInsurance;
    }

    public Unemployed()
    {
        super();
        this.unemploymentInsurance = 0.0;
    }

    public double getUnemploymentInsurance() {
        return unemploymentInsurance;
    }

    public void setUnemploymentInsurance(double unemploymentInsurance) {
        this.unemploymentInsurance = unemploymentInsurance;
    }

    @Override
    public String data()
    {
        return super.data() + " - " + "R$ " + this.unemploymentInsurance;
    }

    @Override
    public String[] listData()
    {
        String[] data = {super.getName(), super.getSurname(), super.getCpf(), String.valueOf(this.unemploymentInsurance), "Desempregado"};
        return data;
    }

}
