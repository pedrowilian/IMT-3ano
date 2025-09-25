package Aula101;

// Complexidade: O(n)
import java.util.Arrays;

public class RevertArray {
    public static void main(String[] args) {
        Integer[] x = new Integer[9];
        for (int i = 0; i < x.length; i++) {
            x[i] = i + 1;
        }
        System.out.println("Original: " + Arrays.toString(x));
        System.out.println("Revertido: " + Arrays.toString(reorder(x)));
    }

    /**
     * Inverte a ordem de um array
     * @param array array original
     * @return mesmo array, com elementos trocados de ponta a ponta
     */
    public static Integer[] reorder(Integer[] array) {
        int n = array.length;
        int meio = n / 2;
        // Troca pares simétricos: n/2 trocas, cada uma O(1)
        for (int i = 0; i < meio; i++) {
            Integer temp = array[i];
            array[i] = array[n - 1 - i];
            array[n - 1 - i] = temp;
        }
        return array;
    }
}