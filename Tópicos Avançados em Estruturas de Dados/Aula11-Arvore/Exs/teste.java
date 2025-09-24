public class teste {
    public static void main(String[] args) {
        Node_Tree node4 = new Node_Tree(4);
        Node_Tree node1 = new Node_Tree(1);
        Node_Tree node2 = new Node_Tree(2);
        Node_Tree node3 = new Node_Tree(3);
        Node_Tree node8 = new Node_Tree(8);
        Node_Tree node21 = new Node_Tree(21);
        Node_Tree node12 = new Node_Tree(12);
        Node_Tree node7 = new Node_Tree(7);
        Node_Tree node0 = new Node_Tree(0);
        Node_Tree node6 = new Node_Tree(6);

        Tree tree = new Tree();
        tree.insertRoot(5);

        tree.root.firstChild = node4;
        node4.parent = tree.root;
        node4.nextSibling = node3;
        node3.parent = tree.root;
        node3.nextSibling = node7;
        node7.parent = tree.root;

        node4.firstChild = node1;
        node1.parent = node4;
        node1.nextSibling = node2;
        node2.parent = node4;

        node3.firstChild = node8;
        node8.parent = node3;
        node8.firstChild = node21;
        node21.parent = node8;
        node21.nextSibling = node12;
        node12.parent = node8;

        node7.firstChild = node0;
        node0.parent = node7;
        node0.nextSibling = node6;
        node6.parent = node7;
        tree.size = 11;
        System.out.println("Imprime pre-ordem");
        tree.root.preorder();

        System.out.println("Imprime filhos do nó 5");
        tree.root.printChildNodes();

        System.out.println("Imprime valor do pai do nó 8");
        node8.printParentNode();

        System.out.println("Imprime filhos do nó 8 após dobrar");
        node8.doubleChildNodes();
        node8.printChildNodes();

        System.out.println("Imprime valor do pai do nó 2 após dobrar");
        node2.doubleParentNode();
        node2.printParentNode();
    }
}
