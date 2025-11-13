package Aula212;

import java.util.Scanner;

public class Teste_Grafo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Construção de um Grafo Direcionado ===");
        System.out.print("Quantos vértices o grafo terá? ");
        int qtdVertices = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        // 1) Cria o grafo direcionado com a quantidade informada
        Grafo_Direcional g = new Grafo_Direcional(qtdVertices);

        // 2) Entrada das arestas
        System.out.print("\nQuantas arestas (ligações direcionadas) deseja adicionar? ");
        int qtdArestas = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        System.out.println("\nDigite as ligações no formato: origem destino");
        System.out.println("(Ex: '1 3' representa uma aresta de 1 -> 3)\n");

        for (int i = 0; i < qtdArestas; i++) {
            System.out.print("Aresta " + (i + 1) + ": ");
            int origem = sc.nextInt();
            int destino = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            if (origem < 1 || origem > qtdVertices || destino < 1 || destino > qtdVertices) {
                System.out.println("Vértices inválidos! Use valores entre 1 e " + qtdVertices + ".");
                i--; // repete a iteração
                continue;
            }

            g.addAresta(origem, destino);
        }

        // 3) Exibe o grafo
        System.out.println("\n=== MATRIZ DE ADJACÊNCIA ===");
        System.out.println(g.retornaGrafo());

        // 4) Teste de verificação de aresta
        System.out.println("=== Teste de existência de arestas ===");
        System.out.print("Deseja verificar se existe uma aresta (s/n)? ");
        String resposta = sc.nextLine().trim().toLowerCase();

        while (resposta.equals("s")) {
            System.out.print("Informe a origem: ");
            int origem = sc.nextInt();
            System.out.print("Informe o destino: ");
            int destino = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            System.out.println("Existe " + origem + " -> " + destino + "? " + g.ehAresta(origem, destino));

            System.out.print("Deseja verificar outra aresta (s/n)? ");
            resposta = sc.nextLine().trim().toLowerCase();
        }

        // 5) Teste de remoção
        System.out.print("\nDeseja remover alguma aresta (s/n)? ");
        resposta = sc.nextLine().trim().toLowerCase();

        while (resposta.equals("s")) {
            System.out.print("Informe a origem da aresta a remover: ");
            int origem = sc.nextInt();
            System.out.print("Informe o destino da aresta a remover: ");
            int destino = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            g.removeAresta(origem, destino);

            System.out.println("\n=== MATRIZ APÓS REMOÇÃO ===");
            System.out.println(g.retornaGrafo());

            System.out.print("Deseja remover outra aresta (s/n)? ");
            resposta = sc.nextLine().trim().toLowerCase();
        }

        // Finalização
        System.out.println("\n=== MATRIZ FINAL ===");
        System.out.println(g.retornaGrafo());

        System.out.println("Programa finalizado!");
        sc.close();
    }
}
