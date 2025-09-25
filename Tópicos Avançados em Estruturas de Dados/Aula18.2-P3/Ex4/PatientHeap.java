public class PatientHeap {
    private final Patient[] heap;
    private int size;
    private final int capacity;

    public PatientHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Patient[capacity];
        this.size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Patient temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(Patient patient) {
        if (size >= capacity) {
            System.out.println("Erro: Fila de prioridades cheia!");
            return;
        }
        heap[size] = patient;
        int i = size;
        size++;

        while (i > 0 && heap[parent(i)].getPriority() > heap[i].getPriority()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public Patient remove() {
        if (size == 0) {
            return null;
        }

        Patient root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        if (size > 0) {
            heapify(0);
        }
        return root;
    }

    private void heapify(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left].getPriority() < heap[smallest].getPriority()) {
            smallest = left;
        }
        if (right < size && heap[right].getPriority() < heap[smallest].getPriority()) {
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