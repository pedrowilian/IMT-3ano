package Aula103;

// Lista circular com nó sentinela (head).
// Operações: insereFirst, insereLast, deleteFirst, deleteLast, imprimeFirst/Last, imprimeLista.
// Complexidades:
//  - insereFirst/insereLast: O(1)
//  - deleteFirst/deleteLast: O(1)
//  - imprimeFirst/imprimeLast: O(1)
//  - imprimeLista: O(n)

/**
 * Lista circular usando sentinela em head.
 */
public class CList {
    /** Quantidade de elementos (não inclui sentinela). */
    public int size;
    /** Nó sentinela: head.next é primeiro, head.prev é último. */
    public DListNode head;

    /**
     * Construtor: cria sentinela apontando para si mesmo.
     * <p>Complexidade: O(1)</p>
     */
    public CList() {
        head = new DListNode();
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /**
     * Insere item logo após a sentinela (início).
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void insereFirst(int item) {
        DListNode novo = new DListNode(item);
        // liga novo entre sentinela e antigo primeiro
        novo.next = head.next;
        novo.prev = head;
        head.next.prev = novo;
        head.next = novo;
        size++;
    }

    /**
     * Insere item antes da sentinela (fim).
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void insereLast(int item) {
        DListNode novo = new DListNode(item);
        novo.next = head;
        novo.prev = head.prev;
        head.prev.next = novo;
        head.prev = novo;
        size++;
    }

    /**
     * Remove o primeiro elemento (head.next).
     * <p>Complexidade: O(1)</p>
     */
    public void deleteFirst() {
        if (size == 0) {
            System.out.println("Del inválida: lista vazia...");
            return;
        }
        DListNode rem = head.next;
        head.next = rem.next;
        rem.next.prev = head;
        size--;
    }

    /**
     * Remove o último elemento (head.prev).
     * <p>Complexidade: O(1)</p>
     */
    public void deleteLast() {
        if (size == 0) {
            System.out.println("Del inválida: lista vazia...");
            return;
        }
        DListNode rem = head.prev;
        head.prev = rem.prev;
        rem.prev.next = head;
        size--;
    }

    /**
     * Imprime o primeiro item (head.next).
     * <p>Complexidade: O(1)</p>
     */
    public void imprimeFirst() {
        if (size == 0) System.out.println("Lista vazia...");
        else System.out.println("Primeiro: " + head.next.item);
    }

    /**
     * Imprime o último item (head.prev).
     * <p>Complexidade: O(1)</p>
     */
    public void imprimeLast() {
        if (size == 0) System.out.println("Lista vazia...");
        else System.out.println("Último: " + head.prev.item);
    }

    /**
     * Imprime todos os elementos de head.next a head.prev.
     * <p>Complexidade: O(n)</p>
     */
    public void imprimeLista() {
        if (size == 0) {
            System.out.println("Lista vazia...");
            return;
        }
        System.out.print("Lista circular: (");
        DListNode p = head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + (i < size-1 ? ", " : ""));
            p = p.next;
        }
        System.out.println(")");
    }
}


