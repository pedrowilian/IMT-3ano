public class Node {
    int item;
    Node next;

    // Default constructor
    public Node(){
        this.item = 0;
        this.next = null;
    }
    // Constructor with parameters
    public Node(int item) {
        this.item = item;
        this.next = null;
    }
    // Insert a new node at end
    public void insertEnd(int newItem) {
        Node newNode = new Node(newItem);
        Node current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Print linked list
    public void printList() {
        Node current = this;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }
    // Find in linked list
    public Node find(int value) {
        Node current = this;
        while (current != null) {
            if (current.item == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}

