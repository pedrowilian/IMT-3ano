public class SList {
    private Node head;

    public SList() {
        head = null;
    }

    // Insert a new node at the end/beginning
    public void insereInicio (int newItem) {
        if (head == null)
            head = new Node(newItem);
        else
            head.insertEnd(newItem);
    }

    // Print the linked list
    public void printList() {
        if (head != null) {
            head.printList();
        }
    }

    // Find a node with a specific value
    public Node find(int value) {
        if (head != null) {
            return head.find(value);
        }
        return null;
    }
}
