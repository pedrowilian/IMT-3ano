package Aula211;

public class Teste_Grafo {
    public static void main(String[] args) {
        // Criação dos vértices 
        Vertice v1 = new Vertice("Campinas");
        Vertice v2 = new Vertice("São Paulo");
        Vertice v3 = new Vertice("Campos do Jordão");
        Vertice v4 = new Vertice("São Lourenço");
        Vertice v5 = new Vertice("Poços de Caldas");

        // Criação do grafo 
        Grafo g = new Grafo();           
        g.addVertice(v1);
        g.addVertice(v2);
        g.addVertice(v3);
        g.addVertice(v4);
        g.addVertice(v5);

        // === CONEXÕES ===
        // Como o grafo é NÃO orientado, adicionamos ida e volta 
        ligaNaoOrientada(v1, v2); // Campinas <-> São Paulo
        ligaNaoOrientada(v1, v5); // Campinas <-> Poços de Caldas

        ligaNaoOrientada(v2, v3); // São Paulo <-> Campos do Jordão
        ligaNaoOrientada(v2, v5); // São Paulo <-> Poços de Caldas
        ligaNaoOrientada(v2, v4); // São Paulo <-> São Lourenço

        ligaNaoOrientada(v4, v5); // São Lourenço <-> Poços de Caldas
        ligaNaoOrientada(v4, v3); // São Lourenço <-> Campos de Jordão

        // === Implementação das funções e testes ===

        System.out.println("=== imprimeVerticesDoGrafo ===");
        g.imprimeVerticesDoGrafo();

        System.out.println("\n=== imprimeArestasDeVertice (v1) ===");
        g.imprimeArestasDeVertice(v1);

        System.out.println("\n=== retornaGrauVertice / imprimeGrauVertice ===");
        System.out.println("retornaGrauVertice(v1): " + g.retornaGrauVertice(v1));
        g.imprimeGrauVertice(v1);

        System.out.println("\n=== imprimeGrafo ===");
        g.imprimeGrafo();

        System.out.println("\n=== removeVertice (v3) ===");
        g.removeVertice(v3);

        System.out.println("\n=== imprimeGrafo (após remover v3) ===");
        g.imprimeGrafo();

        System.out.println("\n=== imprimeArestasDeVertice (v2, após remoção de v3) ===");
        g.imprimeArestasDeVertice(v2);
    }

    // Função para criar duas arestas (u->w e w->u) num grafo não orientado
    private static void ligaNaoOrientada(Vertice u, Vertice w/*, int peso */) {
        Aresta uw = new Aresta(u, w);
        Aresta wu = new Aresta(w, u);
        u.addAresta(uw);
        w.addAresta(wu);
    }
}
