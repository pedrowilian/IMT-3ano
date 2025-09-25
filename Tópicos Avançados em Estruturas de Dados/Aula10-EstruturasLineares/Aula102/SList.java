package Aula102;

// Classe que mantém controle de uma lista ligada (head e size)

/**
 * Classe de lista simplesmente ligada (SList) com operações básicas:
 *  - insere no início/fim
 *  - imprime toda a lista
 *  - deleta do início/fim
 *  - altera item do nó
 * 
 * Internamente mantém:
 *  - head: referência ao primeiro Node
 *  - size: número de nós na lista
 */
public class SList {
    /** Cabeça da lista (primeiro nó) */
    private Node head;
    /** Quantidade de elementos na lista */
    private int size;

    /**
     * Construtor inicializa lista vazia.
     * Complexidade: O(1)
     */
    public SList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Insere um novo valor no início da lista.
     * @param item valor a inserir
     * Complexidade: O(1) — apenas ajusta referências.
     */
    public void insereInicio(int item) {
        Node novo = new Node(item);
        // Novo nó aponta para o antigo head
        novo.next = this.head;
        
        // Atualiza head para o novo nó
        this.head = novo;
        this.size++;
    }

    /**
     * Insere um novo valor no final da lista.
     * @param item valor a inserir
     * Complexidade: O(n) — percorre até o último nó.
     */
    public void insereFim(int item) {
        Node novo = new Node(item);
        if (this.head == null) {
            this.head = novo;
        } 
        else {
            Node trav = this.head;
            // Avança até o último nó
            while (trav.next != null) {
                trav = trav.next;
            }
            trav.next = novo;
        }
        this.size++;
    }

    /**
     * Imprime todos os elementos da lista na ordem encadeada.
     * Complexidade: O(n) — visita cada nó uma vez.
     */
    public void imprimeLista() {
        System.out.print("Lista: ");
        Node trav = this.head;
        // Itera até trav ser null
        while (trav != null) {
            System.out.print(trav.item + " -> ");
            trav = trav.next;
        }
        System.out.println("null");
    }

    /**
     * Deleta o primeiro nó da lista.
     * Complexidade: O(1) — ajusta head.
     */
    public void deleteInicio() {
        if (this.head == null) {
            System.out.println("Lista vazia, nada a deletar.");
            return;
        }
        // head aponta para segundo nó
        this.head = this.head.next;
        this.size--;
    }

    /**
     * Deleta o último nó da lista.
     * Complexidade: O(n) — precisa do predecessor do último.
     */
    public void deleteFim() {
        if (this.head == null) {
            System.out.println("Lista vazia, nada a deletar.");
        } else if (this.head.next == null) {
            // Apenas um nó
            this.head = null;
            this.size--;
        } else {
            Node trav = this.head;
            // Avança até penúltimo nó
            while (trav.next.next != null) {
                trav = trav.next;
            }
            // Desvincula último
            trav.next = null;
            this.size--;
        }
    }

    /**
     * Altera o valor armazenado no primeiro nó.
     * @param item novo valor
     * Complexidade: O(1)
     */
    public void alteraInicio(int item) {
        if (this.head == null) {
            System.out.println("Lista vazia, nada a alterar.");
        } else {
            this.head.item = item;
        }
    }

    /**
     * Altera o valor armazenado no último nó.
     * @param item novo valor
     * Complexidade: O(n) — encontra o último nó.
     */
    public void alteraFim(int item) {
        if (this.head == null) {
            System.out.println("Lista vazia, nada a alterar.");
        } else {
            Node trav = this.head;
            while (trav.next != null) {
                trav = trav.next;
            }
            trav.item = item;
        }
    }

    /**
     * Retorna o tamanho atual da lista.
     * @return número de nós
     * Complexidade: O(1)
     */
    public int comprimento() {
        return this.size;
    }
}
