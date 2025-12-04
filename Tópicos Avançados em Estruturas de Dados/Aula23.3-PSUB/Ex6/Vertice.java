import java.util.ArrayList;

public class Vertice {
    String dado;
    ArrayList<Aresta> listaArestas;
    
    private Vertice emparelhadoCom = null; 

    public Vertice(String dado, ArrayList<Aresta> listaArestas) {
        this.dado = dado;
        this.listaArestas = listaArestas;
    }

    public Vertice(String dado) {
        this.dado = dado;
        this.listaArestas = new ArrayList<>();
    }

    public String getDado() {
        return dado;
    }
    public ArrayList<Aresta> getListaArestas() {
        return listaArestas;
    }
    
    public Vertice getEmparelhadoCom() {
        return emparelhadoCom;
    }

    public void setEmparelhadoCom(Vertice emparelhadoCom) {
        this.emparelhadoCom = emparelhadoCom;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }
    public void setListaArestas(ArrayList<Aresta> listaArestas) {
        this.listaArestas = listaArestas;
    }

    public void addAresta(Aresta a) {
        this.listaArestas.add(a);
    }

    public boolean removeAresta(Aresta a) {
        if (this.listaArestas == null) {
            return false;
        }
        return this.listaArestas.remove(a);
    }
}