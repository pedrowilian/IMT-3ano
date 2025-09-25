package Aula101;

// Complexidade: O(n) no pior caso, O(1) no melhor caso
public class BuscaLinear {
    public static void main(String[] args) {
        Integer[] x = new Integer[50];
        for (int i = 0; i < x.length; i++) {
            x[i] = i + 1;
        }
        int argumento = 50;
        int pos = buscaLinearArray(x, argumento);
        if (pos == -1) {
            System.out.println("Valor não existente no array...");
        } else {
            System.out.println("Valor " + argumento + " encontrado na posição: " + pos);
        }
    }

    /**
     * Busca linear em um array
     * @param array array de inteiros onde buscar
     * @param argumento valor a ser encontrado
     * @return índice do valor ou -1 se não encontrado
     */
    public static int buscaLinearArray(Integer[] array, int argumento) {
        int n = array.length;
        // Percorre cada elemento até encontrar ou chegar ao fim
        for (int i = 0; i < n; i++) {
            if (array[i] == argumento) {
                return i; // encontrado em i comparações
            }
        }
        return -1; // não encontrado após n comparações
    }
}