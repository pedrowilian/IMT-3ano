package Aula103;

// Classe que representa um nó em uma lista duplamente ligada.
// Complexidade de criação: O(1)

/**
 * Nó para listas duplamente ligadas (DList).
 * Cada nó conhece seu predecessor e sucessor.
 */
public class DListNode {
    /** Valor armazenado no nó */
    public int item;
    /** Próximo nó na direção forward */
    public DListNode next;
    /** Nó anterior na direção backward */
    public DListNode prev;

    /**
     * Construtor padrão: item=0, next=null, prev=null.
     */
    public DListNode() {
        this(0, null, null);
    }

    /**
     * Construtor com valor, sem referências definidas.
     * @param item valor a armazenar
     */
    public DListNode(int item) {
        this(item, null, null);
    }

    /**
     * Construtor completo.
     * @param item valor a armazenar
     * @param next referência ao próximo nó
     * @param prev referência ao nó anterior
     */
    public DListNode(int item, DListNode next, DListNode prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}