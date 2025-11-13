package Aula212;

// Conclusão: O grafo fornecido ele é Não Direcional, pois as arestas não possuem direção.

public class Teste_Grafo {

    public static void main(String[] args) {
        // 1) Cria um grafo DIRECIONAL com 5 vértices
        Grafo_Direcional g = new Grafo_Direcional(5);

        // 2) Adiciona as arestas conforme a matriz da figura
        // Linha 1 → 0 1 0 0 1
        g.addAresta(1, 2);
        g.addAresta(1, 5);

        // Linha 2 → 1 0 1 1 1
        g.addAresta(2, 1);
        g.addAresta(2, 3);
        g.addAresta(2, 4);
        g.addAresta(2, 5);

        // Linha 3 → 0 1 0 1 0
        g.addAresta(3, 2);
        g.addAresta(3, 4);

        // Linha 4 → 0 1 1 0 1
        g.addAresta(4, 2);
        g.addAresta(4, 3);
        g.addAresta(4, 5);

        // Linha 5 → 1 1 0 1 0
        g.addAresta(5, 1);
        g.addAresta(5, 2);
        g.addAresta(5, 4);

        // 3) Exibe a matriz de adjacência completa
        System.out.println("Matriz de Adjacência (grafo direcionado):");
        System.out.println(g.retornaGrafo());

        // 4) Testa a existência de algumas arestas
        System.out.println("\nTestes com ehAresta(i, j):");
        System.out.println("Existe 1 -> 2? " + g.ehAresta(1, 2)); 
        System.out.println("Existe 3 -> 1? " + g.ehAresta(3, 1)); 
        System.out.println("Existe 4 -> 5? " + g.ehAresta(4, 5)); 
        System.out.println("Existe 5 -> 4? " + g.ehAresta(5, 4)); 

        // 5) Remove algumas arestas para testar
        g.removeAresta(4, 5);
        g.removeAresta(1, 5);

        System.out.println("\nMatriz após remover 4->5 e 1->5:");
        System.out.println(g.retornaGrafo());

        // 6) Verifica se as remoções foram aplicadas corretamente
        System.out.println("\nApós remoção:");
        System.out.println("Existe 4 -> 5? " + g.ehAresta(4, 5)); 
        System.out.println("Existe 1 -> 5? " + g.ehAresta(1, 5)); 
    }
}
