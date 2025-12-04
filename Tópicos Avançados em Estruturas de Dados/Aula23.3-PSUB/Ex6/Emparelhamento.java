import java.util.ArrayList;
import java.util.List;

public class Emparelhamento {
    
    private final Grafo grafo;
    private final List<Vertice> setA; 
    private final List<Vertice> setB; 

    public Emparelhamento(Grafo grafo, List<Vertice> pacientes, List<Vertice> medicos) {
        this.grafo = grafo;
        this.setA = pacientes;
        this.setB = medicos;
    }

    private boolean buscarCaminhoAumentante(Vertice u, ArrayList<Vertice> visitado) {
        for (Aresta aresta : u.getListaArestas()) {
            Vertice v = aresta.getDestino(); 
            
            if (setB.contains(v) && !visitado.contains(v)) {
                visitado.add(v); 

                if (v.getEmparelhadoCom() == null || buscarCaminhoAumentante(v.getEmparelhadoCom(), visitado)) {
                    
                    v.setEmparelhadoCom(u); 
                    u.setEmparelhadoCom(v);
                    return true; 
                }
            }
        }
        return false; 
    }

    public List<String[]> calcularEmparelhamentoMaximo() {
        int emparelhamentosCount = 0;
    
        for (Vertice v : grafo.getListaVertices()) {
            v.setEmparelhadoCom(null);
        }

        for (Vertice paciente : setA) {
            ArrayList<Vertice> visitado = new ArrayList<>();
            
            if (buscarCaminhoAumentante(paciente, visitado)) {
                emparelhamentosCount++;
            }
        }
        List<String[]> resultado = new ArrayList<>();
        for (Vertice paciente : setA) {
            if (paciente.getEmparelhadoCom() != null) {
                resultado.add(new String[]{paciente.getDado(), paciente.getEmparelhadoCom().getDado()});
            }
        }
        
        return resultado;
    }
}