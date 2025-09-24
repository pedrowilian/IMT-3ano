public class BinaryTreeNode {
    public Integer item;
    public BinaryTreeNode parent;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public int sumArit = 0;

    // Constructor
    public BinaryTreeNode(Integer item){
        this.item = item;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    // Get the left child
    public BinaryTreeNode left(){
        if(this.left == null)
            return null;
        else return this.left;
    }

    // Check if the node has a left child
    public boolean hasLeft(){
        return this.left != null;
    }

    // Get the right child
    public BinaryTreeNode right(){
        if(this.right == null)
            return null;
        else return this.right;
    }

    // Check if the node has a right child
    public boolean hasRight(){
        return this.right != null;
    }

    // Preorder
    // Steps and prints and then goes from left to right
    public void binaryPreorder(){
        System.out.println(this.item);
        sumArit += this.item;  // acumula o valor
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
        System.out.println(this.item);
    }

    // Inorder Traversal
    // Prints "volta tanto da esquerda quanto da direita"
    public void binaryInorder(){
        if(this.hasLeft())
            this.left.binaryInorder();
        System.out.println(this.item);
        if(this.hasRight())
            this.right.binaryInorder();
    }

    // Check if a value is in the tree "Searching"
    public boolean inTree(int k) {
        // Base case: if the current node's value matches k, we found it
        if (this.item == k)
            return true;
        // Search in the left subtree if it exists
        if (this.hasLeft() && this.left.inTree(k))
            return true;
        // Search in the right subtree if it exists
        if (this.hasRight() && this.right.inTree(k))
            return true;
        // Value not found in this branch
        return false;
    }

    // Find the maximum value in the tree
    public int binaryMax(){
        int max = this.item;
        if(this.hasLeft()){
            int maxLeft = this.left.binaryMax();
            max = Math.max(max, maxLeft);
        }
        if(this.hasRight()){
            int maxRight = this.right.binaryMax();
            max = Math.max(max, maxRight);
        }
        return max;
    }

    // Find the minimum value in the tree
    public int binaryMin(){
        int min = this.item;
        if(this.hasLeft()){
            int maxLeft = this.left.binaryMin();
            min = Math.min(min, maxLeft);
        }
        if(this.hasRight()){
            int maxRight = this.right.binaryMin();
            min = Math.min(min, maxRight);
        }
        return min;
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
    private int sumNodes(BinaryTreeNode node) {
        if (node == null)
            return 0;
        return node.item + sumNodes(node.left) + sumNodes(node.right);
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

    // Count the number of nodes in the tree
    public int totalNodes() {
        int total = 1;

        if (this.hasLeft())
            total += this.left.totalNodes();

        if (this.hasRight())
            total += this.right.totalNodes();

        return total;
    }

    // Count the number of leaf nodes in the tree
    public int totalLeaves() {
        if (!this.hasLeft() && !this.hasRight())
            return 1; // é uma folha

        int total = 0;

        if (this.hasLeft())
            total += this.left.totalLeaves();

        if (this.hasRight())
            total += this.right.totalLeaves();

        return total;
    }

    // Calculate the height of the tree
    public int height() {
        int leftHeight;
        int rightHeight;
        // Verifica se existe subárvore esquerda
        if (this.hasLeft())
            leftHeight = this.left.height();
        else
            leftHeight = 0;
        // Verifica se existe subárvore direita
        if (this.hasRight())
            rightHeight = this.right.height();
        else
            rightHeight = 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
