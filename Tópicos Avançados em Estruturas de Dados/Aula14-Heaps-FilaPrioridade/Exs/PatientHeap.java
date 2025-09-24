public class PatientHeap {
    private final Patient[] heap; // Heap of patients
    private int size;

    // Constructor
    public PatientHeap(int capacity) {
        heap = new Patient[capacity];
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        Patient temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int getSize() {
        return size;
    }
    // Insert a patient into the heap
    public void insert(Patient p) {
        if (size == heap.length) {
            System.out.println("Heap is full!");
            return;
        }
        heap[size] = p;
        int i = size;
        size++;

        // Heapify-up
        while (i > 0 && heap[parent(i)].priority > heap[i].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Remove the most prioritized patient (root of heap)
    public Patient remove() {
        if (size == 0) return null;

        Patient root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapify(0);
        return root;
    }

    private void heapify(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left].priority < heap[smallest].priority) {
            smallest = left;
        }
        if (right < size && heap[right].priority < heap[smallest].priority) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}