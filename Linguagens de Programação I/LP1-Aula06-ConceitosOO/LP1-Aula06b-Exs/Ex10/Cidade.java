public class Cidade {
    private String nome;
    private String estado;
    private int populacao;
    // Construtor Padrão
    public Cidade() {
        nome = "Sem Nome";
        estado = "Sem Estado";
        populacao = 0;
    }
    // Construtor Parametrizado
    public Cidade(String n, String e, int p) {
        nome = n;
        estado = e;
        populacao = p;
    }

    // Métodos de acesso
    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getPopulacao() {
        return populacao;
    }

    // Métodos modificadores
    public void setNome(String n) {
        nome = n;
    }

    public void setEstado(String e) {
        estado = e;
    }

    public void setPopulacao(int p) {
        populacao = p;
    }
}
