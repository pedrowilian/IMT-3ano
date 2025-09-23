public class Agencia {
    private String nome;
    private int numero;
    private int digito;

    public Agencia(String n, int num, int dig){
        nome = n;
        numero = num;
        digito = dig;
    }
    //métodos modificadores
    public void setNome(String n){
        nome=n;
    }
    public void setNumero(int num){
        numero=num;
    }
    public void setDigito(int dig){
        digito = dig;
    }
    //métodos de acesso
    public String getNome(){
        return nome;
    }
    public int getNumero(){
        return numero;
    }
    public int getDigito(){
        return digito;
    }

}
