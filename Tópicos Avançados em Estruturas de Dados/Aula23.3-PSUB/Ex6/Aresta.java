public class Aresta {
    Vertice origem;
    Vertice destino; 
    
    public Aresta(Vertice origem, Vertice destino) { 
        this.origem = origem;
        this.destino = destino;
    }

    public Vertice getOrigem() {
        return origem;
    }
    public Vertice getDestino() {
        return destino;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }
}