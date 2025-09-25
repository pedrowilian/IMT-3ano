package Aula101;

// Complexidade: O(n)
public class ImprimindoArray {
    public static void main(String[] args) {
        Integer[] x = new Integer[50];
        // Preenche o array com valores de 0 a 49
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        imprimeArray(x);
    }

    /**
     * Imprime todos os elementos de um array
     * @param array array de inteiros a ser impresso
     */
    public static void imprimeArray(Integer[] array) {
        int n = array.length;
        // Percorre todo o array: n operações de println
        for (int i = 0; i < n; i++) {
            System.out.println(array[i]);
        }
    }
}
