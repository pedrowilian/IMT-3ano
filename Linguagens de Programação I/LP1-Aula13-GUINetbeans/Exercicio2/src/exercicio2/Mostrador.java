
package exercicio2;

public class Mostrador {
    
    private int valor;  // Valor atual do mostrador (hora ou minuto)
    private int limite; // Limite máximo (24 para horas, 60 para minutos)

    public Mostrador(int limite) {
        this.limite = limite;
        valor = 0;
    }

    // Incrementa o valor atual e reinicia para 0 ao atingir o limite
    public void incrementar() {
        valor = (valor + 1) % limite;
    }

    // Retorna o valor atual
    public int getValor() {
        return valor;
    }

    // Retorna o limite do mostrador
    public int getLimite(){
        return limite;
    }

    // Define um novo valor
    public void setValor(int valor){
        this.valor = valor;
    }

    // Define um novo limite
    public void setLimite(int limite) {
        this.limite = limite;
    }

    // Retorna o valor como string com 2 dígitos
    public String mostra(){
        if (valor < 10) {
            return "0" + valor;
        } 
        else {
            return "" + valor;
        }
    }
}
