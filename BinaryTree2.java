// TODO: Improve class name
public class BinaryTree2 {
    private Node root = null;

    public BinaryTree2() {}

    // For creating arbitrary trees
    public BinaryTree2(Node root) {
        this.root = root;
    }
    
    public void add(int val) {
        Node n = new Node(val);

        if (root == null) {
            root = n;
        }
        else {
            addHelper(n, root);
        }
    }

    private void addHelper(Node newNode, Node current) {
        if (newNode.getValue() <= current.getValue()) {
            if (current.getLeft() == null) {
                current.setLeft(newNode);
            }
            else {
                addHelper(newNode, current.getLeft());
            }
        }
        else {
            if (current.getRight() == null) {
                current.setRight(newNode);
            }
            else {
                addHelper(newNode, current.getRight());
            }
        }
    }

    public boolean isValid() {
        return isValidHelper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private boolean isValidHelper(Node curr, double min, double max) {
        if (curr == null) {
            return true;
        }
        return curr.getValue() > min && curr.getValue() <= max &&
                isValidHelper(curr.getLeft(), min, curr.getValue()) &&
                isValidHelper(curr.getRight(), curr.getValue(), max);
    }

    // inorder traversal
    public void printTree() {
        if (root != null) {
            printTreeHelper(root);
            System.out.println();
        }
    }

    public void printTreeHelper(Node curr) {
        if (curr.getLeft() != null) {
            printTreeHelper(curr.getLeft());
        }
        System.out.print(curr.getValue() + " ");
        if (curr.getRight() != null) {
            printTreeHelper(curr.getRight());
        }
    }

    public static void main(String[] args) {
        BinaryTree2 t = new BinaryTree2();
        t.add(4);
        t.add(2);
        t.add(7);
        t.add(9);
        t.printTree();
        System.out.println(t.isValid()); // True

        Node n = new Node(5);
        Node n2 = new Node(3);
        Node n3 = new Node(2);
        Node n4 = new Node(6);
        n.setLeft(n2);
        n2.setLeft(n3);
        n2.setRight(n4);
        BinaryTree2 t2 = new BinaryTree2(n);
        t2.printTree();
        System.out.println(t2.isValid()); // False

        BinaryTree2 t3 = new BinaryTree2();
        System.out.println(t3.isValid()); // True
    }

    private static class Node {
        private int val;
        private Node left = null;
        private Node right = null;

        public Node(int v) {
            val = v;
        }

        public int getValue() {
            return val;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
        
        public void setLeft(Node l) {
            left = l;
        }

        public void setRight(Node r) {
            right = r;
        }
    }
}
