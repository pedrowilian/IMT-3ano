public class Filme {
    // Atributos
    private String nome;
    private String diretor;
    private int anoDeLancamento;
    // Construtor Padrão
    public Filme() {
        nome = "Sem Filme";
        diretor = "Sem Diretor";
        anoDeLancamento = 0;
    }
    // Construtor Parametrizado
    public Filme(String n, String d, int ano) {
        nome = n;
        diretor = d;
        anoDeLancamento = ano;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    // Setters
    public void setNome(String n) {
        nome = n;
    }

    public void setDiretor(String d) {
        diretor = d;
    }

    public void setAnoDeLancamento(int ano) {
        anoDeLancamento = ano;
    }
}
