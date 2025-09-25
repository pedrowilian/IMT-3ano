public class Node_Tree {
    public char item;
    public Node_Tree parent;
    public Node_Tree firstChild;
    public Node_Tree nextSibling;

    public Node_Tree(char item){
        this.item = item;
        this.parent = null;
        this.firstChild = null;
        this.nextSibling = null;
    }
}