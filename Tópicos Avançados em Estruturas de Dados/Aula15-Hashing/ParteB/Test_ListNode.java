public class Test_ListNode {

    public static void main(String[] args) {
        // Creating the linked list
        SList list = new SList();
        list.insertEnd(10);
        list.insertEnd(20);
        list.insertEnd(30);

        // Print the linked list
        list.printList();
    }
}
