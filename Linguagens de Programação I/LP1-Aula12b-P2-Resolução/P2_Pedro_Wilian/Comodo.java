public class Comodo {
    private String nome;
    private double consumo;
    private double custo;

    public Comodo(String nome, double consumo) {
        this.nome = nome;
        this.consumo = consumo;
        this.custo = 0.0;
    }

    public void calcularCusto(double valorKwh) {
        this.custo = valorKwh * consumo;
    }

    public String getNome() { 
        return nome; 
    }

    public double getConsumo() { 
        return consumo;
    }
    public double getCusto() { 
        return custo; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public void setConsumo(double consumo) { 
        this.consumo = consumo; 
    }
}
