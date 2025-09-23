public class Boleto extends Pagamento {
    String numero_boleto;
    private int dia;
    private int mes;
    private int ano;

    public Boleto(String nome, String cpf, double valorAserpago, String numero_boleto, int dia, int mes, int ano) {
        super(nome, cpf, valorAserpago);
        this.numero_boleto = numero_boleto;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    public String getNumeroBoleto() {
        return numero_boleto;
    }
    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAno(){
        return ano;
    }
    public void setNumeroBoleto(String numero_boleto){
        this.numero_boleto = numero_boleto;
    }
    public void setDia(int dia){
        this.dia = dia;
    }
    public void setMes(int mes){
        this.mes = mes;
    }
    public void setAno(int ano){
        this.ano = ano;
    }


}

