public class Produto {
    // Atributos
    private String nome;
    private double preco;
    private int quantidade;
    // Método construtor
    public Produto(String n, double p, int quanti){
        nome = n;
        preco = p;
        quantidade = quanti;
    }
    // Método de acesso
    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public int getQuantidade(){
        return quantidade;
    }
    // Método modificadores
    public void setNome(String n){
        nome = n;
    }
    public void setPreco(double p){
        preco = p;
    }
    public void setQuantidade(int q){
        quantidade = q;
    }
    
}
