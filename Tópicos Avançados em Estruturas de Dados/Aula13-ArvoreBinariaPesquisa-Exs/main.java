public class main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Ex1 Create BinarySearchTree from the array  {3,7,8,9,10,5}
        int[] values = {3, 7, 8, 9, 10, 5};
        for (int value : values) {
            bst.addNode(value);
        }

        // Ex2 Insert int value k in BST
        int k = 6;
        bst.addNode(k);

        // Ex3 Print the BST in Preorder
        System.out.println("Preorder:");
        bst.root.binaryPreorder();

        // Ex4 Print the BST in Postorder
        System.out.println("Postorder:");
        bst.root.binaryPostorder();

        // Ex5 Print the BST in Inorder
        System.out.println("Inorder:");
        bst.root.binaryInorder();

        // Ex6 Search for a value of k
        int searchKey = 8;
        BinarySearchTreeNode foundNode = bst.searchKey(searchKey);
        if (foundNode != null)
            System.out.println("Found: " + foundNode.key);
        else
            System.out.println("Not found: " + searchKey);
        
        // Ex7 Search smallest key in bst
        int min = bst.root.searchMin();
        System.out.println("Min key: " + min);

        // Ex8 Amount of nodes in BST
        int total = bst.root.totalNodes();
        System.out.println("Total Nodes: "+total);
        
        // Ex9 Mean of values in BST
        double mean = bst.root.meanBST();
        System.out.printf("Mean: %.2f\n", mean);

        // Ex10 Print the height
        int height = bst.root.height();
        System.out.println("Height: " + height);

        // Ex11 Sum of nulls
        int nulls = bst.root.totalNulls();
        System.out.println("Total Nulls: " + nulls);

        // Ex12 Prints all Values of BST multiples of 2
        System.out.println("Valores múltiplos de 2: ");
        bst.root.printMultiplesOfTwo();

        // Ex13 Print the sum of all values in the BST
        int sum = bst.root.sumNodes(bst.root);
        System.out.println("Sum of all values: " + sum);
    }
}
