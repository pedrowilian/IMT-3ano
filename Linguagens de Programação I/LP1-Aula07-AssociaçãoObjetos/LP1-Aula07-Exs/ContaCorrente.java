
import javax.swing.JOptionPane;

public class ContaCorrente {
    private int numero;
    private int digito;
    private Agencia agencia;
    private double saldo;

    public ContaCorrente(int n, int d, Agencia a, double s){
        numero = n;
        digito = d;
        agencia = a;
        saldo = s;
    }
    //métodos modificadores
    public void setNumero(int n){
        numero = n;
    }
    public void setDigito(int d){
        digito = d;
    }
    public void setAgencia(Agencia a){
        agencia = a;
    }
    public void setSaldo(double s){
        saldo = s;
    }
    //métodos de acesso
    public int getNumero(){
        return numero;
    }
    public int getDigito(){
        return digito;
    }
    public Agencia getAgencia(){
        return agencia;
    }
    public double getSaldo(){
        return saldo;
    }
    public void depositar(double deposito){
        saldo = saldo + deposito;
        setSaldo(saldo);
    }
    public void sacar(double saque){
        saldo = saldo-saque;
        if(saldo<0){
            JOptionPane.showMessageDialog(null, "Saque não foi efetuado", "Erro",JOptionPane.ERROR_MESSAGE);
            saldo = 0;
        }
        setSaldo(saldo);
    }
    public String consultarSaldo(){
        return "Saldo da conta: "+getSaldo();
    }
    public String imprimirSaldo(){
        return "Número da conta com dígito: "+numero+"-"+digito
               +"\n"+
               "Saldo: "+getSaldo()+ "\n"+
               "Número da agência com dígito: "+agencia.getNumero()+"-"+agencia.getDigito();
    }
   
}
