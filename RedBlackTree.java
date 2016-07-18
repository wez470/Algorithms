import java.util.HashSet;

/*
 * Based of the wikipedia algorithm.  Graphviz can be used
 * to visualize the tree
 */
public class RedBlackTree {
    public enum Color { RED, BLACK }
    private Node root;

    public RedBlackTree() {
    }

    public void add(int val) {
        Node n = new Node(val);
        addHelper(root, n);
        updateTree(n);
    }

    private void addHelper(Node curr, Node newNode) {
        if (curr == null) {
            root = newNode;
            return;
        }

        if (newNode.value < curr.value) {
            if (curr.left == null) {
                curr.left = newNode;
                newNode.parent = curr;
            }
            else {
                addHelper(curr.left, newNode);
            }
        }
        else if (newNode.value > curr.value) {
            if (curr.right == null) {
                curr.right = newNode;
                newNode.parent = curr;
            }
            else {
                addHelper(curr.right, newNode);
            }
        }
    }

    private void updateTree(Node n) {
        if (n.parent == null) {
            n.color = Color.BLACK;
        }
        else {
            updateTree2(n);
        }
    }
    
    private void updateTree2(Node n) {
        if (n.parent.color == Color.BLACK) {
            return; // Tree is still valid
        }
        else {
            updateTree3(n); 
        }
    }

    private void updateTree3(Node n) {
        Node uncle = getUncle(n);

        if ((uncle != null) && (uncle.color == Color.RED)) {
            n.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            Node grandparent = getGrandparent(n);
            grandparent.color = Color.RED;
            updateTree(grandparent);
        } else {
            updateTree4(n);
        }
    }

    private Node getUncle(Node n) {
        Node grandparent = getGrandparent(n);
        if (grandparent != null) {
            if (grandparent.left == n.parent) {
                return grandparent.right;
            } else {
                return grandparent.left;
            }
        }
        return null;
    }

    private Node getGrandparent(Node n) {
        if (n == null) {
            return null;
        }
        else if (n.parent == null) {
            return null;
        }
        else {
            return n.parent.parent;
        }
    }

    private void updateTree4(Node n) {
        Node g = getGrandparent(n);

        if ((n == n.parent.right) && (n.parent == g.left)) {
            rotateLeft(n.parent);
            n = n.left;
        } else if ((n == n.parent.left) && (n.parent == g.right)) {
            rotateRight(n.parent);
            n = n.right; 
        }
        updateTree5(n);  
    }

    private void rotateLeft(Node n) {
        if (n == root) {
            Node savedRoot = root;
            Node savedLeft = n.right.left;
            root = root.right;
            root.left = savedRoot;
            savedRoot.right = savedLeft;
            root.left.parent = root;
            root.right.parent = root;
            root.parent = null;
            if (savedLeft != null) {
                savedLeft.parent = n;
            }
        }
        else {
            Node savedLeft = n.right.left;
            Node savedParent = n.parent;
            n.parent.right = n.right;
            n.right.parent = savedParent;
            n.right.left = n;
            n.parent = n.right;
            n.right = savedLeft;
            if (savedLeft != null) {
                savedLeft.parent = n;
            }
        }
    }

    private void rotateRight(Node n) {
        if (n == root) {
            Node savedRoot = root;
            Node savedRight = n.left.right;
            root = root.left;
            root.right = savedRoot;
            savedRoot.left = savedRight;
            root.left.parent = root;
            root.right.parent = root;
            root.parent = null;
            if (savedRight != null) {
                savedRight.parent = n;
            }
        }
        else {
            Node savedRight = n.left.right;
            Node savedParent = n.parent;
            n.parent.left = n.right;
            n.left.parent = savedParent;
            n.left.right = n;
            n.parent = n.left;
            n.left = savedRight;
            if (savedRight != null) {
                savedRight.parent = n;
            }
        }
    }

    private void updateTree5(Node n) {
        Node g = getGrandparent(n);
        n.parent.color = Color.BLACK;
        g.color = Color.RED;
        if (n == n.parent.left) {
            rotateRight(g);
        }
        else {
            rotateLeft(g);
        }
    }

    public void printTree() {
        System.out.println("digraph G {");
        printHelper(root, new HashSet<Node>());
        System.out.println("}");
    }

    private void printHelper(Node n, HashSet<Node> nodes) {
        if (nodes.contains(n)) {
            return;
        }
        nodes.add(n);

        if (n.parent != null) {
            System.out.println("\t" + "\"" + n.value + "-" + n.color + "\" -> \"" + n.parent.value
                    + "-" + n.parent.color + "\" [color=red];");
        }
        if (n.left != null) {
            System.out.println("\t" + "\"" + n.value + "-" + n.color + "\" -> \"" + n.left.value
                    + "-" + n.left.color + "\";");

            printHelper(n.left, nodes);
        }
        if (n.right != null) {
            System.out.println("\t" + "\"" + n.value + "-" + n.color + "\" -> \""
                    + n.right.value + "-" + n.right.color + "\";");
            printHelper(n.right, nodes);
        }

    }

    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();
        for (int i = 0; i < 21; i++) {
            t.add(i);
        }
        t.printTree();
    }

    private class Node {
        private Node parent;
        private Node left;
        private Node right;
        private int value;
        private Color color;

        public Node(int value) {
            this.value = value;
            color = Color.RED;
            left = null;
            right = null;
            parent = null;
        }
    }

}
