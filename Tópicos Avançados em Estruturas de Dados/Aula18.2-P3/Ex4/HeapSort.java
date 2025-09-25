
import java.util.Arrays;

public class HeapSort {
    public static int size; 

    public static void main(String[] args){
        int[] array = {5,6,2,1,9,10,12,0,3,7,14,99,34,77};
        System.out.println("Array before HeapSort: \n");
        System.out.println(Arrays.toString(array));
        System.out.println("\nArray after HeapSort: \n");
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void maxHeapify (int[] A, int father){
        int left = 2*father + 1;
        int right = 2*father + 2;
        int largest = father;

        if (left <= size && A[left] > A[largest]) { 
            largest = left;
        }
        if (right <= size && A[right] > A[largest]) { 
            largest = right;
        }
        if (largest != father) { 
            int aux = A[father];
            A[father] = A[largest];
            A[largest] = aux;
            maxHeapify(A, largest);
        }
    }

    public static void buildMaxHeap(int[] A){
        size = A.length - 1; 
        for (int father = (A.length/2); father >= 0; father--){ 
            maxHeapify(A, father);
        }
    }

    public static void heapSort(int[] A){
        buildMaxHeap(A);
        for (int i = size; i > 0; i--){ 
            int aux = A[0];
            A[0] = A[i];
            A[i] = aux;
            size--;
            maxHeapify(A, 0);
        }
    }
    
}
