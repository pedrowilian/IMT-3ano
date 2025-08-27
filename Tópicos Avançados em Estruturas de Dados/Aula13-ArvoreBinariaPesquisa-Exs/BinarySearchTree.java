public class BinarySearchTree {
    public BinarySearchTreeNode root;
    public int size;

    // Constructor to initialize the tree
    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    // Insert the root node
    public void insertRoot(int valor){
        BinarySearchTreeNode node = new BinarySearchTreeNode(valor);
        this.root = node;
        this.size = 1;
    }

    // Add a node to the binary search tree
    public void addNode(int key) {
        BinarySearchTreeNode newNode = new BinarySearchTreeNode(key);
        if (root == null)
            this.insertRoot(key);
        else {
            BinarySearchTreeNode NodeTrab = this.root;
            while (true) {
                if (key < NodeTrab.key) {
                    if (NodeTrab.left == null) {
                        NodeTrab.left = newNode;
                        newNode.parent = NodeTrab;
                        return;
                    } else
                        NodeTrab = NodeTrab.left;
                } else {
                    if (NodeTrab.right == null) {
                        NodeTrab.right = newNode;
                        newNode.parent = NodeTrab;
                        return;
                    } else
                        NodeTrab = NodeTrab.right;
                }
            }
        }
    }

    // Search Tree for key
    public BinarySearchTreeNode searchKey(int key){
        BinarySearchTreeNode nodeTrab = this.root;

        while (nodeTrab != null && nodeTrab.key != key){
            if (key < nodeTrab.key)
                nodeTrab = nodeTrab.left;
            else
                nodeTrab = nodeTrab.right;
        }
    return nodeTrab;
    }

    // Get the root node
    public BinarySearchTreeNode getRoot(){
        return (this.root);
    }

    // Get the size of the tree
    public int size(){
        return this.size;
    }

    // Check if the tree is empty
    public boolean isEmpty(){
        return this.size == 0;
    }
}
