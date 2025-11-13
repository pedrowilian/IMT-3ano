package Aula211;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Grafo {
    // Atributos
    ArrayList<Vertice> listaVertices;

    // Construtor padrão
    public Grafo() {
        this.listaVertices = new ArrayList<>();
    }

    // Construtor parametrizado
    public Grafo(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    // Adicionar Vértice ao Grafo 
    public void addVertice(Vertice v) {
        this.listaVertices.add(v);
    }

    // Imprimir Arestas de um Vértice
    public void imprimeArestasDeVertice(Vertice v) {
        // Verifica se o vértice existe no grafo 
        if (this.listaVertices == null || !this.listaVertices.contains(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return;
        }

        // Verifica se o vértice possui arestas 
        if (v.getListaArestas() == null || v.getListaArestas().isEmpty()) {
            System.out.println("Vértice sem arestas.");
            return;
        }

        // Obtém a lista de arestas do vértice
        ArrayList<Aresta> arestas = v.getListaArestas();

        // Imprime as arestas
        for (int i = 0; i < arestas.size(); i++) {
            Aresta a = arestas.get(i);  // pega a aresta da posição i
            Vertice o = a.getOrigem();  // obtém o vértice origem
            Vertice d = a.getDestino(); // obtém o vértice destino

            // Origem
            String so;
            if (o == null) {
                so = "null";
            } 
            else {
                so = o.getDado();
            }

            // Destino
            String sd;
            if (d == null) {
                sd = "null";
            } 
            else {
                sd = d.getDado();
            }

            // Imprime a aresta
            System.out.println(so + " -> " + sd);
        }
    }

    // Imprimir todos os Vértices do Grafo
    public void imprimeVerticesDoGrafo() {
        // Verifica se o grafo possui vértices
        if (this.listaVertices == null || this.listaVertices.isEmpty()) {
            System.out.println("Grafo sem vértices...");
            return;
        }

        // Imprime os vértices
        for (int i = 0; i < this.listaVertices.size(); i++) {
            Vertice v = this.listaVertices.get(i);  // pega o vértice da posição i
            System.out.println(v.getDado());        
        }
    }

    // Retornar o grau de um Vértice
    public Integer retornaGrauVertice(Vertice v) {
        // Verifica se o vértice existe no grafo 
        if (this.listaVertices == null || !this.listaVertices.contains(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return null;
        }

        // Retorna o grau do vértice
        return v.listaArestas.size();
    }

    // Imprimir o grau de um Vértice
    public void imprimeGrauVertice(Vertice v) {
        // Verifica se o vértice existe no grafo
        if (this.listaVertices == null || !this.listaVertices.contains(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return;
        }
        // Obtém o grau do vértice
        Integer grau = retornaGrauVertice(v);

        System.out.println("Grau do vértice " + v.getDado() + ": "   + grau);
    }

    // Remover Vértice do Grafo
    public boolean removeVertice(Vertice v) {
        // Verifica se o vértice existe no grafo
        if (this.listaVertices == null || !this.listaVertices.contains(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return false;
        }
        
         // Remove arestas incidentes a v em todos os outros vértices
        for (int i = 0; i < this.listaVertices.size(); i++) {
            Vertice outro = this.listaVertices.get(i);  // pega o vértice da posição i

            ArrayList<Aresta> la = outro.getListaArestas(); // obtém a lista de arestas do vértice

            // Remove de trás para frente para evitar problemas com o índice ao remover
            for (int j = la.size() - 1; j >= 0; j--) {
                Aresta a = la.get(j);   // pega a aresta da posição j

                if (a.getOrigem() == v || a.getDestino() == v) {
                    la.remove(j);
                }
            }
        }

        // Remove o próprio vértice
        boolean ok = this.listaVertices.remove(v);
        if (ok) {
            System.out.println("Vértice removido com sucesso");
        }
        return ok;
    }

    // Imprimir o Grafo - Trocado de boolean para void
    public void imprimeGrafo() {
        // Verifica se o grafo possui vértices
        if (this.listaVertices == null || this.listaVertices.isEmpty()) {
            System.out.println("Grafo sem vértices...");
            return;
        }

        // Imprime os vértices e suas arestas
        for (int i = 0; i < this.listaVertices.size(); i++) {
            Vertice v = this.listaVertices.get(i);  // pega o vértice da posição i
            System.out.println("Vértice: " + v.getDado());

            ArrayList<Aresta> arestas = v.getListaArestas();    // obtém a lista de arestas do vértice
            
            // Imprime as arestas
            // Verifica se o vértice possui arestas
            if (arestas == null || arestas.isEmpty()) {
                System.out.println("  (sem arestas)");
            } 
            else {
                // Imprime as arestas
                for (int j = 0; j < arestas.size(); j++) {
                    Aresta a = arestas.get(j);  // pega a aresta da posição j
                    Vertice o = a.getOrigem();  // obtém o vértice origem
                    Vertice d = a.getDestino(); // obtém o vértice destino

                     // Origem
                    String so;
                    if (o == null) {
                        so = "null";
                    } else {
                        so = o.getDado();
                    }

                    // Destino
                    String sd;
                    if (d == null) {
                        sd = "null";
                    } else {
                        sd = d.getDado();
                    }

                    // Imprime a aresta
                    System.out.println( so + " -> " + sd);
                }
            }
        }
    }
    // =============================
    // ALGORITMO DE DIJKSTRA
    // =============================
    public Map<Vertice, Double> dijkstra(Vertice origem) {
        Map<Vertice, Double> dist = new HashMap<>();
        Map<Vertice, Vertice> anterior = new HashMap<>();
        Set<Vertice> visitados = new HashSet<>();

        // Inicializa todas as distâncias como "infinito"
        for (Vertice v : listaVertices) {
            dist.put(v, Double.POSITIVE_INFINITY);
        }
        dist.put(origem, 0.0);

        // Fila de prioridade com base na menor distância atual
        PriorityQueue<Vertice> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
        pq.add(origem);

        while (!pq.isEmpty()) {
            Vertice atual = pq.poll();

            if (visitados.contains(atual))
                continue;

            visitados.add(atual);

            for (Aresta a : atual.getArestas()) {
                Vertice vizinho = a.getDestino();
                double novaDist = dist.get(atual) + a.getPeso();

                if (novaDist < dist.get(vizinho)) {
                    dist.put(vizinho, novaDist);
                    anterior.put(vizinho, atual);
                    pq.add(vizinho);
                }
            }
        }

        return dist;
    }

    //Função auxiliar para imprimir caminho mínimo
    public void imprimeMenorCaminho(Vertice origem, Vertice destino){
        Map<Vertice, Double> dist = dijkstra(origem);
        Map<Vertice, Vertice> anterior = reconstruirCaminho(origem);

        if (dist.get(destino) == Double.POSITIVE_INFINITY) {
            System.out.println("Não existe caminho entre " + origem + " e " + destino);
            return;
        }

        List<Vertice> caminho = new ArrayList<>();
        for(Vertice v = destino; v != null; v = anterior.get(v)){
            caminho.add(v);
        }
        Collections.reverse(caminho);

        System.out.println("Menor caminho de " + origem + " até " + destino + ": " + caminho);
        System.out.println("Distância total: " + dist.get(destino));
    }

    // Auxiliar: reconstroi o caminho (requer ajuste para armazenar o mapa 'anterior' como atributo)
    private Map<Vertice, Vertice> reconstruirCaminho(Vertice origem){
        //Exemplo simples: para uso didático, o ideal seria adaptar o dijkstra() para retornar ambos os mapas
        return new HashMap<>();
    }
    




    public void buscaEmLargura(Vertice inicio) {
        // 1) Verifica se o vértice inicial é válido
        if (inicio == null || !this.listaVertices.contains(inicio)) {
            System.out.println("Vértice inicial inexistente no Grafo...");
            return;
        }

        // 2) Lista de visitados e fila
        ArrayList<Vertice> visitados = new ArrayList<>();
        Queue<Vertice> fila = new LinkedList<>();

        // 3) Marca o vértice inicial como visitado e o adiciona à fila
        visitados.add(inicio);
        fila.add(inicio);

        System.out.println("Busca em Largura a partir de: " + inicio.getDado());

        // 4) Enquanto houver vértices na fila
        while (!fila.isEmpty()) {
            Vertice atual = fila.poll(); // remove o primeiro da fila
            System.out.println("Visitando: " + atual.getDado());

            // 5) Percorre todas as arestas do vértice atual
            for (Aresta a : atual.getListaArestas()) {
                Vertice destino = a.getDestino();

                // 6) Se o destino ainda não foi visitado, adiciona à fila
                if (!visitados.contains(destino)) {
                    visitados.add(destino);
                    fila.add(destino);
                }
            }
        }
    }
    
}
