// Class representing a node in a tree
public class Node_Tree {
    public int item;
    public Node_Tree parent;
    public Node_Tree firstChild;
    public Node_Tree nextSibling;

    // Constructor to initialize the node
    public Node_Tree(int item){
        this.item = item;
        this.parent = null;
        this.firstChild = null;
        this.nextSibling = null;
    }

    // Getter for the parent node
    public Node_Tree Parent(){
        if(this.parent == null)
            return null;
        else return (this.parent);
    }

    // Print the parent node
    public void printParentNode(){
        if(this.parent != null)
            System.out.println("Pai: " + this.parent.item);
        else
            System.out.print("Este nó é root, não tem pai.");
    }

    // Print the child nodes
    public void printChildNodes(){
        if(this.firstChild == null)
            System.out.print("Este node não tem filhos.");
        else{
            Node_Tree trab = this.firstChild;
            while(trab != null){
                System.out.println("Filhos: " + trab.item);
                trab = trab.nextSibling;
            }
        }
    }

    // Check if the node is internal
    public boolean isInternal(){
        return this.firstChild != null;
    }

    // Get the number of ancestors to the node
    public int depth(){
        if(this.parent == null){
            return 0;
        }
        else return ( 1+this.parent.depth() );
    }

    // Get the height of the node
    public int height(){
        if(this.firstChild == null)
            return 0;

        int h = 0;
        Node_Tree trab = this.firstChild;

        while(trab.nextSibling != null){
            h = Math.max(h, trab.nextSibling.height());
            trab = trab.nextSibling;
        }
        return 1+h;
    }

    // Preorder traversal O(n)
    // Steps and prints and then goes from left to right
    public void preorder(){
        System.out.println(this.item);

        Node_Tree trab = this.firstChild;

        while(trab != null){
            trab.preorder();
            trab = trab.nextSibling;
        }
    }
    // Postorder traversal o(n)
    public void postorder(){
        Node_Tree trab = this.firstChild;

        while(trab != null){
            trab.postorder();
            trab = trab.nextSibling;
        }
        System.out.println(this.item);
    }

    // Print the child nodes that are leaves
    public void printChildLeaves(){
        if(this.firstChild == null)
            System.out.print("Este node não tem filhos.");
        else{
            Node_Tree trab = this.firstChild;
            while(trab != null){
                if(trab.firstChild == null){
                    System.out.println("Filhos e folhas: " + trab.item);
                }
                trab = trab.nextSibling;
            }
        }
    }

    // Double the Child nodes
    public void doubleChildNodes(){
        Node_Tree trab = this.firstChild;

        while (trab != null) {
            trab.item = trab.item * 2;
            trab = trab.nextSibling;
        }
    }

    // Double Parent node
    public void doubleParentNode(){
        if(this.parent != null)
            this.parent.item = this.parent.item * 2;
    }
}
