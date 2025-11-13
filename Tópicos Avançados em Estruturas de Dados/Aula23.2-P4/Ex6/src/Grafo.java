import java.util.ArrayList;
import java.util.List;

// Representa o grafo
// Armazena uma lista de todos os Nós

public class Grafo {
    private List<No> nos; 

    public Grafo() {
        this.nos = new ArrayList<>();
    }

    public No adicionarNo(String label) {
        No novoNo = new No(label);
        int index = nos.indexOf(novoNo);
        if (index == -1) {
            nos.add(novoNo);
            return novoNo;
        } else {
            return nos.get(index);
        }
    }

    public No getNo(String label) {
        for (No no : nos) {
            if (no.getLabel().equalsIgnoreCase(label)) {
                return no;
            }
        }
        return null;
    }

    public void adicionarAresta(String labelPartida, String labelChegada) {
        No noPartida = getNo(labelPartida);
        No noChegada = getNo(labelChegada);

        if (noPartida != null && noChegada != null) {
            noPartida.adicionarVizinho(noChegada);
        } else {
            if (noPartida == null) {
                System.err.println("Erro: Nó de partida não encontrado (" + labelPartida + ")");
            }
            if (noChegada == null) {
                System.err.println("Erro: Nó de chegada não encontrado (" + labelChegada + ")");
            }
        }
    }

    public List<No> getPacientesPorMedico(No noMedico) {
        List<No> pacientesInteressados = new ArrayList<>();
        for (No no : nos) {
            if (no.getLabel().startsWith("P")) {
                if (no.getVizinhos().contains(noMedico)) {
                    pacientesInteressados.add(no);
                }
            }
        }
        return pacientesInteressados;
    }
}