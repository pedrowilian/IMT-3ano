public class teste {
    public static void main(String[] args) {
        //Ex1
        BinaryTree tree = new BinaryTree();
        tree.insertRoot(0);
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        tree.root.left = node1;
        tree.root.right = node2;
        node1.parent = tree.root;
        node2.parent = tree.root;
        node2.left = node3;
        node2.right = node4;
        node3.parent = node2;
        node4.parent = node2;
        node3.left = node5;
        node3.right = node6;
        node5.parent = node3;
        node6.parent = node3;
        tree.size = 7;
        //Ex2
        System.out.println("Pre - Order:");
        tree.root.binaryPreorder();
        System.out.println("Pos - Order:");
        tree.root.binaryPostorder();
        System.out.println("In - Order:");
        tree.root.binaryInorder();
        
        //Ex3
        int i = 7;
        System.out.println("Está na árvore? " + tree.root.inTree(i));

        //Ex4
        System.out.println("Maior valor da árvore: " + tree.root.binaryMax());

        //Ex5
        System.out.println("Menor valor da árvore: " + tree.root.binaryMin());

        //Ex6
        System.out.println("Média dos valores na árvore: " + tree.root.meanBST());

        //Ex7
        System.out.println("Nulos na árvore: " + tree.root.totalNulls());

        //Ex8
        System.out.println("Quantidade de nós: " + tree.root.totalNodes());

        //Ex9
        System.out.println("Quantidade de nós folhas: " + tree.root.totalLeaves());

        //Ex10
        System.out.println("Altura de árvore: " + tree.root.height());
    }
}
