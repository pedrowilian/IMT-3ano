public class BinarySearchTreeNode {
    Integer key;
    BinarySearchTreeNode parent;
    BinarySearchTreeNode left;
    BinarySearchTreeNode right;

    // Constructor
    public BinarySearchTreeNode(Integer key) {
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    // Get the left child
    public BinarySearchTreeNode left(){
        if(this.left == null)
            return null;
        else return this.left;
    }

    // Check if the node has a left child
    public boolean hasLeft(){
        return this.left != null;
    }

    // Get the right child
    public BinarySearchTreeNode right(){
        if(this.right == null)
            return null;
        else return this.right;
    }

    // Check if the node has a right child
    public boolean hasRight(){
        return this.right != null;
    }
    // Preorder Traversal
    // Steps and prints and then goes from left to right
    public void binaryPreorder(){
        System.out.println(this.key);
        if(this.hasLeft())
            this.left.binaryPreorder();
        if(this.hasRight())
            this.right.binaryPreorder();
    }
    // Postorder Traversal
    // Prints "Voltando da direita" ou when there is no right child and goes back
    public void binaryPostorder(){
        if(this.hasLeft())
            this.left.binaryPostorder();
        if(this.hasRight())
            this.right.binaryPostorder();
        System.out.println(this.key);
    }

    // Inorder Traversal
    // Prints "volta tanto da esquerda quanto da direita"
    public void binaryInorder(){
        if(this.hasLeft())
            this.left.binaryInorder();
        System.out.println(this.key);
        if(this.hasRight())
            this.right.binaryInorder();
    }

    // Find the minimum value in the tree
    public int searchMin(){
        if(this.hasLeft())
            return this.left.searchMin();
        return this.key;
    }

    // Count the number of nodes in the tree
    public int totalNodes() {
        int total = 1;

        if (this.hasLeft())
            total += this.left.totalNodes();

        if (this.hasRight())
            total += this.right.totalNodes();

        return total;
    }
    // Calculate the arithmetic mean of all values in the BST
    public double meanBST() {
        int sumOfBST = sumNodes(this);
        int numberOfNodes = totalNodes();
        if (numberOfNodes == 0) {
            return 0;
        }
        return (double) sumOfBST / numberOfNodes;
    }

    // Sum of all values in BST
    public int sumNodes(BinarySearchTreeNode node) {
        if (node == null)
            return 0;
        return node.key + sumNodes(node.left) + sumNodes(node.right);
    }

    // Calculate the height of the tree
    public int height() {
        int leftHeight;
        int rightHeight;
        // Checks if there is a left subtree
        if (this.hasLeft())
            leftHeight = this.left.height();
        else
            leftHeight = 0;
        // Checks if there is a right subtree
        if (this.hasRight())
            rightHeight = this.right.height();
        else
            rightHeight = 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Count the number of null children
    public int totalNulls() {
        int nulos = 0;

        if (!this.hasLeft()) nulos++;
        else nulos += this.left.totalNulls();

        if (!this.hasRight()) nulos++;
        else nulos += this.right.totalNulls();

        return nulos;
    }
    // Print all values in the BST that are multiples of 2
    public void printMultiplesOfTwo(){
        if (this.key % 2 == 0)
            System.out.println(this.key);
        if (this.hasLeft()) 
            this.left.printMultiplesOfTwo();
        if (this.hasRight()) 
        this.right.printMultiplesOfTwo();
    }

}
