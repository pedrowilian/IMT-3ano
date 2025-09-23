public class Produto {
    // Atributos
    private String nome;
    private String codigo;
    private double preco;
    // Construtor Padrão
    public Produto() {
        nome = "Produto Padrão";
        codigo = "0000";
        preco = 0.0;
    }
    // Construtor Parametrizado
    public Produto(String n, String c, double p) {
        nome = n;
        codigo = c;
        preco = p;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    // Setters 
    public void setNome(String n) {
        nome = n;
    }

    public void setCodigo(String c) {
        codigo = c;
    }

    public void setPreco(double p) {
        preco = p;
    }
}