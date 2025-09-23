public class Animal {
    private String especie;
    private String nome;
    private int idade;
    // Construtor Padrão
    public Animal() {
        especie = "Desconhecida";
        nome = "Desconhecido";
        idade = 0;
    }
    // Construtor Parametrizado
    public Animal(String e, String n, int i) {
        especie = e;
        nome = n;
        idade = i;
    }

    // Métodos de acesso
    public String getEspecie() {
        return especie;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    // Métodos modificadores
    public void setEspecie(String e) {
        especie = e;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setIdade(int i) {
        idade = i;
    }
}
