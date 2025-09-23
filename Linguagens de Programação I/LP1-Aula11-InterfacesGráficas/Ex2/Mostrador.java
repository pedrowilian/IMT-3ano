package Ex2;

public class Mostrador {

    private int valor;
    private int limite;

    public Mostrador(int limite) {
        this.limite = limite;
        valor = 0;
    }

    public void incrementar() {
        valor = (valor + 1) % limite;
    }
    public void atualizar(int valor){
        if (valor >= 0 && valor < limite) {
            this.valor = valor;
        }
    }
    
    public int getValor() {
        return valor;
    }

    public String mostra(){
        if (valor < 10) {
            return "0" + valor;
        } else {
            return "" + valor;
        }
    }
}

