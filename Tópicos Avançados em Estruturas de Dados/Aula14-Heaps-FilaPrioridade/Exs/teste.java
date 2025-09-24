public class teste {
    public static void main(String[] args) {
        PatientHeap queue = new PatientHeap(20);

        queue.insert(new Patient("Mary", 85, false, false));    // priority 0
        queue.insert(new Patient("John", 70, false, false));    // priority 1
        queue.insert(new Patient("Anna", 30, true, false));     // priority 2
        queue.insert(new Patient("Carlos", 40, false, false));  // priority 3

        System.out.println("Serving patients in order of priority:");
        while (!queue.isEmpty()) {
            System.out.println("Now serving: " + queue.remove());
        }
    }
}
