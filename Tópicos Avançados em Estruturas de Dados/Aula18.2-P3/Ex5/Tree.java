public class Tree {
    public Node_Tree root;
    public int size;

    public Tree(){
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    private Node_Tree findRecursive(Node_Tree node, char c) {
        if (node.item == c) return node;
        Node_Tree child = node.firstChild;
        while (child != null) {
            Node_Tree found = findRecursive(child, c);
            if (found != null) return found;
            child = child.nextSibling;
        }
        return null;
    }

    public Node_Tree find(char c) {
        if (root == null) return null;
        return findRecursive(root, c);
    }

    public void insert(char child, Character parentChar) {
        Node_Tree parent = null;
        if (parentChar != null) {
            parent = find(parentChar);
            if (parent == null) {
                System.out.println("Pai nao encontrado !");
                return;
            }
        } else {
            if (!isEmpty()) {
                System.out.println("Nao pode inserir raiz em uma arvore nao vazia");
                return;
            }
        }

        if (find(child) != null) {
            System.out.println("Caractere Duplicado");
            return;
        }

        Node_Tree newNode = new Node_Tree(child);
        newNode.parent = parent;

        if (parent == null) { 
            root = newNode;
            size = 1;
        } else {
            if (parent.firstChild == null) {
                parent.firstChild = newNode;
            } else {
                Node_Tree sib = parent.firstChild;
                while (sib.nextSibling != null) {
                    sib = sib.nextSibling;
                }
                sib.nextSibling = newNode;
            }
            size++;
        }
    }

    public String getPreOrder() {
        if (isEmpty()) {
            return "Árvore Vazia";
        }
        StringBuilder sb = new StringBuilder();
        preOrderCollect(root, sb);
        return sb.toString().substring(0, sb.length() - 2);
    }

    private void preOrderCollect(Node_Tree node, StringBuilder sb) {
        sb.append(node.item).append(", ");
        Node_Tree trab = node.firstChild;
        while (trab != null) {
            preOrderCollect(trab, sb);
            trab = trab.nextSibling;
        }
    }

    public int size(){
        return this.size;
    }
}