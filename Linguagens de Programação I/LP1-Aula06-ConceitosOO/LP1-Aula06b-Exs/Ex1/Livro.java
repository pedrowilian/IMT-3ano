public class Livro{
    // Atributos
    private String titulo;
    private String autor;
    private int numeroDePaginas;
    // Método construtor padrão
    public Livro(){
        titulo = "Sem título";
        autor = "Sem autor";
        numeroDePaginas = 0;
    }
    // Método construtor com parâmetros
    public Livro(String n, String a, int num){
        titulo = n;
        autor = a;
        numeroDePaginas = num;
    }
    // Método de acesso
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public int getNumeroDePaginas(){
        return numeroDePaginas;
    }

    // Método modificadores
    public void setTitulo(String t){
        titulo = t;
    }
    public void setAutor(String a){
        autor = a;
    }
    public void setNumeroDePaginas(int n){
        numeroDePaginas = n;
    }
}