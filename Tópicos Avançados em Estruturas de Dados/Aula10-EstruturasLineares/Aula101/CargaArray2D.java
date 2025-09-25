package Aula101;

// Complexidade: O(r * c)
public class CargaArray2D {
    public static void main(String[] args) {
        int[][] nums = new int[5][4];
        // Preenche matriz com aleatórios de 0 a 9
        for (int r = 0; r < nums.length; r++) {
            for (int c = 0; c < nums[r].length; c++) {
                nums[r][c] = (int) (Math.random() * 10);
                System.out.print(nums[r][c] + " ");
            }
            System.out.println();
        }
    }
}
