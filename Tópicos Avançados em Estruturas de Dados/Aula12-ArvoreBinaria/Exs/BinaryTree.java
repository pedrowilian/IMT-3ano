// Class representing a tree and its control node
public class BinaryTree {
    public BinaryTreeNode root;
    public int size;

    // Constructor to initialize the tree
    public BinaryTree(){
        this.root = null;
        this.size = 0;
    }

    // Insert the root node
    public void insertRoot(int valor){
        BinaryTreeNode node = new BinaryTreeNode(valor);
        this.root = node;
        this.size = 1;
    }

    // Get the root node
    public BinaryTreeNode getRoot(){
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
