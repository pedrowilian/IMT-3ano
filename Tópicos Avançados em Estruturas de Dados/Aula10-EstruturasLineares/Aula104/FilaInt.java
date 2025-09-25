package Aula104;

// Implementação de fila usando array estático.
// Complexidade:
//  - Enqueue: O(1)
//  - Dequeue: O(n) (deslocamento)
//  - Imprime: O(n)

/**
 * Fila de inteiros implementada com array circular simples.
 * @param fila array de elementos
 * @param front índice do próximo a remover
 * @param rear  índice onde inserir o próximo elemento
 * @param size  número de elementos na fila
 */
public class FilaInt {
    private int[] fila;
    private int front, rear, size;

    /**
     * Constrói uma fila com capacidade n.
     * @param n capacidade máxima
     */
    public FilaInt(int n) {
        fila = new int[n];
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * Enfileira um item no final.
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void enqueue(int item) {
        if (size == fila.length) {
            System.out.println("Queue Overflow! Erro no enqueue...");
            return;
        }
        fila[rear] = item;
        rear = (rear + 1) % fila.length;
        size++;
    }

    /**
     * Desenfileira o item do início.
     * @return valor removido ou null se vazia
     * <p>Complexidade: O(1)</p>
     */
    public Integer dequeue() {
        if (size == 0) {
            System.out.println("Queue empty! Erro no dequeue...");
            return null;
        }
        int valor = fila[front];
        front = (front + 1) % fila.length;
        size--;
        return valor;
    }

    /**
     * Imprime elementos da fila de front a rear-1.
     * <p>Complexidade: O(n)</p>
     */
    public void imprimeFila() {
        System.out.print("Fila: ");
        if (size == 0) {
            System.out.print("vazia!");
        } else {
            for (int i = 0, idx = front; i < size; i++) {
                System.out.print(fila[idx] + " ");
                idx = (idx + 1) % fila.length;
            }
        }
        System.out.println();
    }
}