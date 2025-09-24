// Class representing a tree and its control node
public class Tree {
    public Node_Tree root;
    public int size;

    // Constructor to initialize the tree
    public Tree(){
        this.root = null;
        this.size = 0;
    }

    // Insert the root node
    public void insertRoot(int valor){
        Node_Tree node = new Node_Tree(valor);
        this.root = node;
        this.size = 1;
    }

    // Get the root node
    public Node_Tree getRoot(){
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

