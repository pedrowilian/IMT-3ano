package Aula104;

// Implementação de pilha usando array estático.
// Complexidade:
//  - Push:   O(1)
//  - Pop:    O(1)
//  - Imprime:O(n)

/**
 * Pilha de inteiros implementada com array de tamanho fixo.
 * @param pilha array que armazena os elementos
 * @param topo  índice do topo da pilha (-1 quando vazia)
 */
public class PilhaInt {
    private int[] pilha;
    private int topo;

    /**
     * Constrói uma pilha com capacidade máxima n.
     * @param n capacidade da pilha
     */
    public PilhaInt(int n) {
        pilha = new int[n]; // aloca array
        topo = -1;          // pilha inicialmente vazia
    }

    /**
     * Empilha um item no topo.
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void push(int item) {
        if (topo >= pilha.length - 1) {
            System.out.println("Stack Overflow! Erro no push...");
        } else {
            topo++;
            pilha[topo] = item;
        }
    }

    /**
     * Desempilha o item do topo.
     * @return valor no topo ou null se vazia
     * <p>Complexidade: O(1)</p>
     */
    public Integer pop() {
        if (topo < 0) {
            System.out.println("Stack empty! Erro no pop...");
            return null;
        }
        int valor = pilha[topo];
        topo--;
        return valor;
    }

    /**
     * Imprime todos os elementos da pilha do topo ao fundo.
     * <p>Complexidade: O(n)</p>
     */
    public void imprimePilha() {
        System.out.print("Pilha: ");
        if (topo < 0) {
            System.out.print("vazia!");
        } else {
            for (int i = topo; i >= 0; i--) {
                System.out.print(pilha[i] + " ");
            }
        }
        System.out.println();
    }
}