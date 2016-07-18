public class BinaryTree {
    private BinaryTree left;
    private BinaryTree right;
    private int value;

    public BinaryTree(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public void add(int newVal) {
        if (newVal < value) {
            if (left == null) {
                left = new BinaryTree(newVal);
            }
            else {
                left.add(newVal);
            }
        }
        else if (newVal > value) {
            if (right == null) {
                right = new BinaryTree(newVal);
            }
            else {
                right.add(newVal);
            }
        }
    }

    @Override
    public String toString() {
        return "(" + left + ") " + value + " (" + right + ")";
    }

    public boolean isValid() {
        return (left == null ? true : left.isValidHelper(Integer.MIN_VALUE, value)) && (right == null ? true : right.isValidHelper(value, Integer.MAX_VALUE));
    }

    private boolean isValidHelper(int min, int max) {
        if (value > min && value < max) {
            return (left == null ? true : left.isValidHelper(Integer.MIN_VALUE, value)) && (right == null ? true : right.isValidHelper(value, Integer.MAX_VALUE));
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree(4);
        b.add(2);
        b.add(7);
        b.add(9);
        System.out.println(b);
        System.out.println(b.isValid());
    }
}
