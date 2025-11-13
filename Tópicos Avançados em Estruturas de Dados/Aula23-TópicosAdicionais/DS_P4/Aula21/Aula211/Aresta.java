package Aula211;

public class Aresta {
    // Atributos
    Vertice origem;
    Vertice destino;    

    // Construtor
    public Aresta(Vertice origem, Vertice destino) { // Aresta é formada por um par de vértices
        this.origem = origem;
        this.destino = destino;
    }

    // Getters
    public Vertice getOrigem() {
        return origem;
    }
    public Vertice getDestino() {
        return destino;
    }

    // Setters
    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public Double getPeso() {
        throw new UnsupportedOperationException("Unimplemented method 'getPeso'");
    }
}
