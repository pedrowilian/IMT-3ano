package Aula102;

// Classe que representa um nó em uma lista simplesmente ligada.
/**
 * Representa um nó de lista ligada, contendo um valor inteiro e
 * referência para o próximo nó na lista.
 * 
 * Operações:
 *  - Criação de nó vazio ou com valor.
 */

public class Node {
    /** Valor armazenado no nó */
    public int item;
    /** Referência para o próximo nó (null se não houver) */
    public Node next;

    /**
     * Construtor padrão: cria nó com item = 0 e next = null.
     */
    public Node() {
        this.item = 0;
        this.next = null;
    }

    /**
     * Construtor com inicialização de valor.
     * @param item valor a ser armazenado
     */
    public Node(int item) {
        this.item = item;
        this.next = null;
    }
}