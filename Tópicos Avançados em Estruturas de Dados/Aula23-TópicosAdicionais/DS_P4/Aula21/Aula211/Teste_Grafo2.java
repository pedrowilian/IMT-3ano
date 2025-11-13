package Aula211;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Teste_Grafo2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo g = new Grafo();

        Map<String, Vertice> mapaVertices = new HashMap<>();

        System.out.print("Quantos vértices deseja adicionar? ");
        int qtdVertices = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        // === Entrada dos vértices ===
        for (int i = 0; i < qtdVertices; i++) {
            System.out.print("Digite o nome do vértice " + (i + 1) + ": ");
            String nome = sc.nextLine().trim();
            Vertice v = new Vertice(nome);
            g.addVertice(v);
            mapaVertices.put(nome, v);
        }

        // === Entrada das conexões ===
        System.out.print("\nQuantas ligações (arestas) deseja adicionar? ");
        int qtdLigacoes = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        for (int i = 0; i < qtdLigacoes; i++) {
            System.out.println("\nLigação " + (i + 1) + ":");
            System.out.print("Vértice de origem: ");
            String origem = sc.nextLine().trim();

            System.out.print("Vértice de destino: ");
            String destino = sc.nextLine().trim();

            Vertice vOrigem = mapaVertices.get(origem);
            Vertice vDestino = mapaVertices.get(destino);

            if (vOrigem == null || vDestino == null) {
                System.out.println("Um dos vértices não existe! Tente novamente.");
                i--; // repete esta iteração
                continue;
            }

            ligaNaoOrientada(vOrigem, vDestino);
        }

        // === Exibição ===
        System.out.println("\n=== imprimeVerticesDoGrafo ===");
        g.imprimeVerticesDoGrafo();

        System.out.println("\n=== imprimeGrafo ===");
        g.imprimeGrafo();

        // === Teste de remoção ===
        System.out.print("\nDigite o nome de um vértice para remover: ");
        String nomeRemover = sc.nextLine().trim();
        Vertice vRemover = mapaVertices.get(nomeRemover);
        if (vRemover != null) {
            g.removeVertice(vRemover);
            System.out.println("\n=== imprimeGrafo (após remover " + nomeRemover + ") ===");
            g.imprimeGrafo();
        } else {
            System.out.println("Vértice não encontrado!");
        }

        sc.close();
    }

    // Função para criar duas arestas (u->w e w->u) num grafo não orientado
    private static void ligaNaoOrientada(Vertice u, Vertice w) {
        Aresta uw = new Aresta(u, w);
        Aresta wu = new Aresta(w, u);
        u.addAresta(uw);
        w.addAresta(wu);
    }
}
