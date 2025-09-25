package Aula101;

// Complexidade: O(n^2) na geração e impressão
public class RaggedArray {
    public static void main(String[] args) {
        int NMAX = 5;
        int[][] matriz = new int[NMAX + 1][];
        // Define linhas de tamanhos diferentes
        for (int i = 0; i <= NMAX; i++) {
            matriz[i] = new int[i + 1];
        }
        // Preenche e imprime
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 10);
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}