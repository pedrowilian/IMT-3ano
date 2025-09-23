public class Cliente {
    private int id;
    private String nome;
    private double valorKwh;
    private Comodo[] comodos;

    public Cliente(String nome, double valorKwh) {
        this.nome = nome;
        this.valorKwh = valorKwh;
        this.comodos = new Comodo[3];
    }

    public void adicionarComodo(int index, Comodo comodo) {
        if (index >= 0 && index < 3) {
            comodos[index] = comodo;
            comodo.calcularCusto(valorKwh);
        }
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public String getNome() { 
        return nome; 
    }
    public double getValorKwh() { 
        return valorKwh; 
    }
    public Comodo[] getComodos() { 
        return comodos; 
    }
}
