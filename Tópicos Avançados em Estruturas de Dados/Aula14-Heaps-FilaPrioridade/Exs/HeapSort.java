
import java.util.Arrays;

public class HeapSort {
    public static int size; // Universal variable of size

    // Main for testing
    public static void main(String[] args){
        int[] array = {5,6,2,1,9,10,12,0,3,7,14,99,34,77};
        System.out.println("Array before HeapSort: \n");
        System.out.println(Arrays.toString(array));
        System.out.println("\nArray after HeapSort: \n");
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    // Function to maintain the max-heap which means a node's value is greater than its children's values
    public static void maxHeapify (int[] A, int father){
        int left = 2*father + 1; // Left child index
        int right = 2*father + 2; // Right child index
        int largest = father; // Initialize largest as father

        if (left <= size && A[left] > A[largest]) { // Check if left child is larger than current largest
            largest = left;
        }
        if (right <= size && A[right] > A[largest]) { // Check if right child is larger than current largest
            largest = right;
        }
        if (largest != father) { // If largest is not father
            int aux = A[father];
            A[father] = A[largest];
            A[largest] = aux;
            maxHeapify(A, largest);
        }
    }

    // Function to build the max-heap
    public static void buildMaxHeap(int[] A){
        size = A.length - 1; // Set the size of the heap
        for (int father = (A.length/2); father >= 0; father--){ // Start from the last non-leaf node and go up to the root
            maxHeapify(A, father);
        }
    }

    // Function to perform heap sort
    // HeapSort is O(n log n) time complexity
    public static void heapSort(int[] A){
        buildMaxHeap(A); // Build the max-heap
        for (int i = size; i > 0; i--){ // Extract elements from the heap
            int aux = A[0];
            A[0] = A[i];
            A[i] = aux;
            size--;
            maxHeapify(A, 0);
        }
    }
    
}
