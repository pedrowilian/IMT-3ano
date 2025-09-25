package Aula103;

// -> DList.java
// Lista duplamente ligada com head, tail e size.
// Operações: inserção, remoção, impressão.
// Complexidades:
//  - insereInicio/insereFim: O(1)
//  - deleteFirst/deleteLast: O(1)
//  - imprimeLista/imprimeLista2: O(n)

/**
 * Lista duplamente ligada com sentinela implícita em head/tail.
 */
public class DList {
    /** Número de elementos na lista */
    public int size;
    /** Primeiro nó (head) */
    public DListNode head;
    /** Último nó (tail) */
    public DListNode tail;

    /**
     * Construtor padrão: lista vazia.
     * head e tail = null, size=0.
     */
    public DList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Construtor iniciando lista com um item.
     * @param item valor do único nó
     * Complexidade: O(1)
     */
    public DList(int item) {
        DListNode node = new DListNode(item);
        this.head = node;
        this.tail = node;
        this.size = 1;
    }

    /**
     * Insere um novo nó no início da lista.
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void insereInicio(int item) {
        DListNode novo = new DListNode(item);
        if (size == 0) {
            head = novo;
            tail = novo;
        } else {
            novo.next = head;
            head.prev = novo;
            head = novo;
        }
        size++;
    }

    /**
     * Insere um novo nó no final da lista.
     * @param item valor a inserir
     * <p>Complexidade: O(1)</p>
     */
    public void insereFim(int item) {
        DListNode novo = new DListNode(item);
        if (size == 0) {
            head = novo;
            tail = novo;
        }
        else {
            tail.next = novo;
            novo.prev = tail;
            tail = novo;
        }
        size++;
    }

    /**
     * Imprime todos os elementos do head até tail.
     * <p>Complexidade: O(n)</p>
     */
    public void imprimeLista() {
        System.out.print("Lista forward: (");
        DListNode p = head;
        
        while (p != null) {
            System.out.print(p.item);
            if (p.next != null) System.out.print(", ");
            p = p.next;
        }
        System.out.println(")");
    }

    /**
     * Imprime todos os elementos do tail até head.
     * <p>Complexidade: O(n)</p>
     */
    public void imprimeLista2() {
        System.out.print("Lista backward: (");
        DListNode p = tail;
        while (p != null) {
            System.out.print(p.item);
            if (p.prev != null) System.out.print(", ");
            p = p.prev;
        }
        System.out.println(")");
    }

    /**
     * Remove o primeiro nó (head).
     * <p>Complexidade: O(1)</p>
     */
    public void deleteFirst() {
        if (size == 0) {
            System.out.println("Del inválida: lista vazia...");
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
            this.size--;
        } 
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    /**
     * Remove o último nó (tail).
     * <p>Complexidade: O(1)</p>
     */
    public void deleteLast() {
        if (size == 0) {
            System.out.println("Del inválida: lista vazia...");
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
        } 
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }
}
