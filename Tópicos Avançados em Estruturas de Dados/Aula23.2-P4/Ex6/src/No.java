import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Representa um Nó (Vértice) no grafo
// Pode ser um Paciente ou um Médico como vertice

public class No {
    private String label;
    private List<No> vizinhos;

    public No(String label) {
        this.label = label;
        this.vizinhos = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public List<No> getVizinhos() {
        return vizinhos;
    }

    public void adicionarVizinho(No vizinho) {
        if (!this.vizinhos.contains(vizinho)) {
            this.vizinhos.add(vizinho);
        }
    }

    public String toString() {
        return label;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        No no = (No) obj;
        return Objects.equals(label, no.label);
    }

    public int hashCode() {
        return Objects.hash(label);
    }
}