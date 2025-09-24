public class Ex1{
    public static void insertion_sort(int arr[]){
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
        arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int arr[] = { 1, 23, 21, 8, 2 };
        int n = arr.length;
        insertion_sort(arr);
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}