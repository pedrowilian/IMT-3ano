public class Pessoa {
    private String nome;
    private int idade;
    private double altura;
    // Construtor Padrão
    public Pessoa() {
        nome = "Sem Nome";
        idade = 0;
        altura = 0.0;
    }
    // Construtor Parametrizado
    public Pessoa(String n, int i, double a) {
        nome = n;
        idade = i;
        altura = a;
    }

    // Métodos de acesso
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getAltura() {
        return altura;
    }

    // Métodos modificadores
    public void setNome(String n) {
        nome = n;
    }

    public void setIdade(int i) {
        idade = i;
    }

    public void setAltura(double a) {
        altura = a;
    }
}
