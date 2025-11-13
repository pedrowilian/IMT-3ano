package Aula212;

public class Grafo_Direcional {
    // Atributos
    int numVertices;
    int [][] adjMAtrix; // Matriz de Adjacências
    
    // Construtor
    public Grafo_Direcional(int numVertices) {
        this.numVertices = numVertices;
        this.adjMAtrix = new int[numVertices][numVertices];
    }

    // Getter
    public int getNumVertices() {
        return numVertices;
    }
    
    //  Adcionar Aresta entre os vértices i e j
    public void addAresta(int i, int j) {
        // Convertendo o índice para indicar a origem e destino na matriz
        int origem = i - 1;
        int destino = j - 1;

        // Indicando que há uma aresta entre os vértices i e j
        adjMAtrix[origem][destino] = 1;
    }

    // Remove Aresta entre os vértices i e j
    public void removeAresta(int i, int j) {
        // Convertendo o índice para indicar a origem e destino na matriz
        int origem = i - 1;
        int destino = j - 1;

        // Indicando que há uma aresta entre os vértices i e j
        adjMAtrix[origem][destino] = 0;
    }
    
    // Verifica se existe Aresta entre os vértices i e j
    public boolean ehAresta(int i, int j) {
        // Convertendo o índice para indicar a origem e destino na matriz
        int origem = i - 1;
        int destino = j - 1;

        if (adjMAtrix[origem][destino] == 1) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    // Retorna a exibição do grafo na forma de matriz de adjacências
    public String retornaGrafo() {
        StringBuilder sb = new StringBuilder();

        // Percorre todas as linhas da matriz (cada linha representa um vértice origem)
        for (int i = 0; i < numVertices; i++) {

            // Adição dos números do vértice no início da linha, seguido de ": "
            sb.append(i+1).append(": ");

            // Percorre as colunas da matriz (vértices destino)
            for (int j = 0; j < numVertices; j++) {
                // adiciona o valor (0 ou 1)
                sb.append(adjMAtrix[i][j]); 

                // Adiciona um espaço entre os números (evita o espaço no final da linha)
                if (j < numVertices - 1) {
                    sb.append(" ");
                }
            }

            // Adiciona uma quebra de linha no final (menos na última)
            if (i < numVertices - 1) {
                sb.append("\n");
            }
        }

        // StringBuilder em String 
        return sb.toString();
    }
    // === BUSCA EM PROFUNDIDADE (DFS) ===
    public void buscaEmProfundidade(int verticeInicial) {
        // Verifica se o vértice inicial é válido
        if (verticeInicial < 1 || verticeInicial > numVertices) {
            System.out.println("Vértice inicial inválido! Deve estar entre 1 e " + numVertices);
            return;
        }

        boolean[] visitados = new boolean[numVertices]; // controle dos vértices visitados

        System.out.println("Busca em profundidade a partir do vértice " + verticeInicial + ":");
        dfsRecursivo(verticeInicial - 1, visitados); // ajusta para índice de matriz
    }

    // Método auxiliar recursivo
    private void dfsRecursivo(int v, boolean[] visitados) {
        // Marca o vértice como visitado
        visitados[v] = true;
        System.out.println("Visitando: " + (v + 1));

        // Percorre todos os vértices adjacentes
        for (int i = 0; i < numVertices; i++) {
            // Se existe aresta v -> i e ainda não foi visitado
            if (adjMAtrix[v][i] == 1 && !visitados[i]) {
                dfsRecursivo(i, visitados);
            }
        }
    }
}