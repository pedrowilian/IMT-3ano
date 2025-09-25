package Aula101;

// Operações e complexidades:
// - Construtores: O(1)
// - imprime_Lista, imprime_Primeiro, imprime_Ultimo, imprime_Item: O(n) / O(1)
// - insereItem_Inicio, insereItem_Fim, insereItem, insereItem_MetadeLista: O(n)
// - altera_Ultimo, altera_Primeiro, alteraItem: O(1)
// - deleta_Ultimo, deleta_Primeiro, deleta_Item: O(n)

public class List_Array {
    private int[] lista;
    private int ultimo;

    // Cria lista vazia
    public List_Array() {
        lista = new int[0];
        ultimo = -1;
    }

    // Cria lista com capacidade inicial n
    public List_Array(int n) {
        lista = new int[n];
        ultimo = -1;
    }

    // Imprime toda a lista
    public void imprime_Lista() {
        if (ultimo < 0) {
            System.out.println("Lista vazia");
            return;
        }
        System.out.print("Lista:");
        for (int i = 0; i <= ultimo; i++) {
            System.out.print(" " + lista[i]);
        }
        System.out.println();
    }

    // Imprime primeiro elemento
    public void imprime_Primeiro() {
        if (ultimo < 0) System.out.println("Lista vazia");
        else System.out.println("Primeiro: " + lista[0]);
    }

    // Imprime último elemento
    public void imprime_Ultimo() {
        if (ultimo < 0) System.out.println("Lista vazia");
        else System.out.println("Último: " + lista[ultimo]);
    }

    // Imprime item em posição especificada
    public void imprime_Item(int pos) {
        if (pos < 0 || pos > ultimo) System.out.println("Posição inválida");
        else System.out.println("Item[" + pos + "]: " + lista[pos]);
    }

    // Insere no início (cria novo array, copia elementos): O(n)
    public void insereItem_Inicio(int val) {
        int[] nova = new int[lista.length + 1];
        for (int i = 0; i <= ultimo; i++) nova[i+1] = lista[i];
        nova[0] = val;
        lista = nova;
        ultimo = lista.length - 1;
    }

    // Insere no fim (cria novo array, copia e adiciona): O(n)
    public void insereItem_Fim(int val) {
        int[] nova = new int[lista.length + 1];
        for (int i = 0; i <= ultimo; i++) nova[i] = lista[i];
        nova[lista.length] = val;
        lista = nova;
        ultimo = lista.length - 1;
    }

    // Insere em qualquer posição, copiando prefixo e sufixo: O(n)
    public void insereItem(int val, int pos) {
        if (pos < 0 || pos > ultimo) {
            System.out.println("Posição inválida");
            return;
        }
        int[] nova = new int[lista.length + 1];
        
        for (int i = 0; i < pos; i++) nova[i] = lista[i];
        
        for (int i = pos; i <= ultimo; i++) nova[i+1] = lista[i];

        nova[pos] = val;
        lista = nova;
        ultimo = lista.length - 1;
    }

    // Insere na metade: O(n)
    public void insereItem_MetadeLista(int val) {
        int mid = (ultimo+1)/2;
        insereItem(val, mid);
    }

    // Altera primeiro: O(1)
    public void altera_Primeiro(int val) {
        if (ultimo < 0) System.out.println("Lista vazia");
        else lista[0] = val;
    }

    // Altera último: O(1)
    public void altera_Ultimo(int val) {
        if (ultimo < 0) System.out.println("Lista vazia");
        else lista[ultimo] = val;
    }

    // Altera em posição: O(1)
    public void alteraItem(int val, int pos) {
        if (pos < 0 || pos > ultimo) System.out.println("Posição inválida");
        else lista[pos] = val;
    }

    // Deleta primeiro (cria novo array): O(n)
    public void Deleta_Primeiro() {
        if (ultimo < 0) return;
        int[] nova = new int[lista.length - 1];
        for (int i = 1; i <= ultimo; i++) nova[i-1] = lista[i];
        lista = nova;
        ultimo--;
    }

    // Deleta último: O(1)
    public void Deleta_Ultimo() {
        if (ultimo < 0) return;
        ultimo--;
    }

    // Deleta em posição: O(n)
    public void Deleta_Item(int pos) {
        if (pos < 0 || pos > ultimo) return;

        int[] nova = new int[lista.length - 1];

        for (int i = 0; i < pos; i++) nova[i] = lista[i];
        for (int i = pos+1; i <= ultimo; i++) nova[i-1] = lista[i];
        
        lista = nova;
        ultimo--;
    }

    // =======================
    // === Método de teste ===
    // =======================

    public static void main(String[] args) {
        // Cria lista com capacidade inicial 5 (todos com 0)
        List_Array lista = new List_Array(5);
        lista.imprime_Lista();

        // Testa inserções
        lista.insereItem_Fim(10);
        lista.insereItem_Inicio(5);
        lista.insereItem(7, 1);
        lista.insereItem_MetadeLista(8);
        
        // Exibe a lista completa
        lista.imprime_Lista();
        
        // Exibe primeiro e último
        lista.imprime_Primeiro();
        lista.imprime_Ultimo();
        
        // Altera primeiro e último
        lista.altera_Primeiro(1);
        lista.altera_Ultimo(20);
        lista.imprime_Lista();
        
        // Deleta elementos
        lista.Deleta_Primeiro();
        lista.Deleta_Ultimo();
        //lista.imprime_Lista();
        lista.Deleta_Item(1);
        lista.imprime_Lista();
    }
}

