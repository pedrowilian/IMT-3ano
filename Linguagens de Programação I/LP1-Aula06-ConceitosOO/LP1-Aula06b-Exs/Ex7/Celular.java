public class Celular {
    private String marca;
    private String modelo;
    private int armazenamento; // em GB
    // Construtor Padrão
    public Celular() {
        marca = "Marca Desconhecida";
        modelo = "Modelo Desconhecido";
        armazenamento = 0;
    }
    // Construtor Parametrizado
    public Celular(String m, String mo, int a) {
        marca = m;
        modelo = mo;
        armazenamento = a;
    }

    // Métodos de acesso
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

    // Métodos modificadores
    public void setMarca(String m) {
        marca = m;
    }

    public void setModelo(String mo) {
        modelo = mo;
    }

    public void setArmazenamento(int a) {
        armazenamento = a;
    }
}
