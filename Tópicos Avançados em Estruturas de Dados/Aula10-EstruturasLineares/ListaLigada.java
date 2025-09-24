public class ListaLigada {
    private static final int MAX = 100;
    private int[] chave = new int[MAX];
    private int[] proximo = new int[MAX];
    private int[] anterior = new int[MAX];
    private int livre = 0;
    private int inicio = -1;

    public ListaLigada() {
        for (int i = 0; i < MAX; i++) {
            proximo[i] = -1;
            anterior[i] = -1;
        }
    }

    private int novoNo(int valor) {
        if (livre >= MAX) throw new RuntimeException("Lista cheia");
        chave[livre] = valor;
        return livre++;
    }

    public void inserir(int valor) {
        int novo = novoNo(valor);
        if (inicio == -1) {
            inicio = novo;
        } else {
            int atual = inicio;
            while (proximo[atual] != -1) {
                atual = proximo[atual];
            }
            proximo[atual] = novo;
            anterior[novo] = atual;
        }
    }

    public void deletar(int valor) {
        int atual = inicio;
        while (atual != -1 && chave[atual] != valor) {
            atual = proximo[atual];
        }
        if (atual == -1) return; // não encontrado

        int ant = anterior[atual];
        int prox = proximo[atual];

        if (ant != -1) {
            proximo[ant] = prox;
        } else {
            inicio = prox; // início era o nó atual
        }

        if (prox != -1) {
            anterior[prox] = ant;
        }
    }

    public void imprimir() {
        int atual = inicio;
        while (atual != -1) {
            System.out.print(chave[atual] + " ");
            atual = proximo[atual];
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ListaLigada lista = new ListaLigada();

        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.imprimir(); // 10 20 30

        lista.deletar(20);
        lista.imprimir(); // 10 30
    }
}
