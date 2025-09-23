public class Carro {
    // Atributos
    private String marca;
    private String modelo;
    private int anoDeFabricacao;

    // Construtor Padrão
    public Carro() {
        marca = "Desconhecida";
        modelo = "Desconhecido";
        anoDeFabricacao = 0;
    }
    // Construtor Parametrizado
    public Carro(String ma, String mo, int ano) {
        marca = ma;
        modelo = mo;
        anoDeFabricacao = ano;
    }

    // Métodos de acesso 
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    // Métodos modificadores 
    public void setMarca(String m) {
        marca = m;
    }

    public void setModelo(String m) {
        modelo = m;
    }

    public void setAnoDeFabricacao(int ano) {
        anoDeFabricacao = ano;
    }
}