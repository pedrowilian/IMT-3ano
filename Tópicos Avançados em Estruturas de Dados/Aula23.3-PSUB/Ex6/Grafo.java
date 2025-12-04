import java.util.ArrayList;

public class Grafo {
    ArrayList<Vertice> listaVertices;

    public Grafo() {
        this.listaVertices = new ArrayList<>();
    }

    public Grafo(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public void addVertice(Vertice v) {
        this.listaVertices.add(v);
    }

    public ArrayList<Vertice> getListaVertices() {
        return listaVertices;
    }
    
    public boolean removeVertice(Vertice v) {
        if (this.listaVertices == null || !this.listaVertices.contains(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return false;
        }
        
        for (int i = 0; i < this.listaVertices.size(); i++) {
            Vertice outro = this.listaVertices.get(i);  

            ArrayList<Aresta> la = outro.getListaArestas(); 

            for (int j = la.size() - 1; j >= 0; j--) {
                Aresta a = la.get(j); 

                if (a.getOrigem() == v || a.getDestino() == v) {
                    la.remove(j);
                }
            }
        }
        boolean ok = this.listaVertices.remove(v);
        if (ok) {
            System.out.println("Vértice removido com sucesso");
        }
        return ok;
    }

}