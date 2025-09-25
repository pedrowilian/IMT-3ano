package Aula101;

// Complexidade: O(r * c)
public class PrintArray2D {
    public static void main(String[] args) {
        int[][] nums = new int[5][4];
        // Gera valores
        for (int r = 0; r < nums.length; r++) {
            for (int c = 0; c < nums[r].length; c++) {
                nums[r][c] = (int) (Math.random() * 10);
            }
        }
        printArray2D(nums);
    }

    /**
     * Imprime matriz 2D
     * @param array matriz de inteiros
     */
    public static void printArray2D(int[][] array) {
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                System.out.print(array[r][c] + " ");
            }
            System.out.println();
        }
    }
}
