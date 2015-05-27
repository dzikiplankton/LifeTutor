package generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AvlTree implements Serializable {

    Node root;

    public class Node implements Serializable{

        /**
         * This node's left and right children and this node's parent.
         */
        Node left, right;
        private Node parent;

        String value;
        ArrayList<Integer> keys;

        private int balance, height = 1;

        /**
         * Creates a new node.
         *
         * @param val The value.
         */
        private Node(String val, int key) {
            this.value = val;
            this.keys = new ArrayList<>();
            this.keys.add(key);
        }

        private void setLeft(Node left) {

            this.left = left;
            if (left != null) {
                left.parent = this;
            }

        }

        private void setRight(Node right) {

            this.right = right;
            if (right != null) {
                right.parent = this;
            }

        }

        private void setRoot() {

            AvlTree.this.root = this;
            parent = null;

        }

        /**
         * Replaces a child with another node.
         *
         * @param old The old node.
         * @param rep The new node.
         */
        private void replace(Node old, Node rep) {

            if (left == old) {
                setLeft(rep);
            } else if (right == old) {
                setRight(rep);
            }
        }

        private void update() {

            int[] sonHeight = sonHeight();

            balance = sonHeight[0] - sonHeight[1];
            height = Math.max(sonHeight[0], sonHeight[1]) + 1;

        }

        private int[] sonHeight() {

            return new int[]{
                right == null ? 0 : right.height,
                left == null ? 0 : left.height
            };

        }

        private Node onlySon() {

            if (right != null && left == null) {
                return right;
            } else if (left != null && right == null) {
                return left;
            } else {
                throw new IllegalStateException("This node does not have an only son.");
            }

        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean isSingleSon() {
            return left == null ^ right == null;
        }

        @Override
        public boolean equals(Object o) {

            if (o == null) {
                return false;
            }

            Node n = (Node) o;
            return n.value == this.value
                    && ((this.left == null && n.left == null) || (n.left != null && n.left.equals(n.left)))
                    && ((this.right == null && n.right == null) || (n.right != null && n.right.equals(n.right)));

        }

        /**
         * Recursively prints a node and its children as a string.
         */
        @Override
        public String toString() {
            return "[v:" + value + ",b:" + balance
                    + ",l:" + (left == null ? "#" : left.toString())
                    + ",r:" + (right == null ? "#" : right.toString()) + "]";
        }

        boolean haveKey(int key) {
            for (int i : this.keys) {
                if (i == key) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * Checks if a value was inserted in the tree.
     *
     * @param val The value.
     * @return <code>true</code> if the value was found.
     */
    public Node fromTree(String val) {

        Node c = root;
        while (c != null) // Value match -> End of search.
        {
            if (c.value.equals(val)) {
                return c;

                // Value smaller than current element -> Go left.
            } else if (val.compareTo(c.value) < 0) {
                c = c.left;
                // Value bigger than current element -> Go right.
            } else {
                c = c.right;
            }
        }
        return null;
    }

    /**
     * Adds a new value to this tree.
     *
     * @param val The new value.
     * @param key
     */
    public void add(String val, int key) {
        // No root yet exists -> Insert as root and return.
        if (root == null) {
            new Node(val, key).setRoot();
            return;
        }

        // Find right path for insertion.
        Node c = root;
        while (true) {

            // Element is smaller than current node's value -> Continue at right.
            if (val.compareTo(c.value) < 0) {
                if (c.left == null) {
                    c.setLeft(new Node(val, key));
                    up(c);
                    return;
                } else {
                    c = c.left;
                }

                // Element is bigger than current node's value -> Continue at right.
            } else if (val.compareTo(c.value) > 0) {
                if (c.right == null) {
                    c.setRight(new Node(val, key));
                    up(c);
                    return;
                } else {
                    c = c.right;
                }
            } else {
                c.keys.add(key);
                return;
            }
        }

    }

    /**
     * Walks up from the most recent deleted item to the tree root in order to
     * inform all entities
     *
     * @param a The current node that has to be revalidated after a tree
     * alteration.
     */
    private void up(Node a) {

        Node next = a.parent;
        int orgHeigth = a.height;
        a.update();

        if (Math.abs(a.balance) >= 2) {
            a = rotate(a);
        }

        if (next != null && orgHeigth != a.height) {
            up(next);
        }

    }

    private Node rotate(Node a) {

        if (a.balance == 2) {
            if (a.right.balance == -1) {
                return rotateLeftDouble(a);
            } else {
                return rotateLeft(a);
            }
        } else if (a.left.balance == 1) {
            return rotateRightDouble(a);
        } else {
            return rotateRight(a);
        }

    }

    private Node rotateRight(Node a) {

        Node s = a.left;

        if (a == root) {
            s.setRoot();
        } else {
            a.parent.replace(a, s);
        }

        a.setLeft(s.right);
        a.update();

        s.setRight(a);
        s.update();

        return s;

    }

    private Node rotateLeft(Node a) {

        Node s = a.right;

        if (a == root) {
            s.setRoot();
        } else {
            a.parent.replace(a, s);
        }

        a.setRight(s.left);
        a.update();

        s.setLeft(a);
        s.update();

        return s;

    }

    private Node rotateLeftDouble(Node a) {

        Node s = a.right;
        Node b = s.left;

        if (a == root) {
            b.setRoot();
        } else {
            a.parent.replace(a, b);
        }

        a.setRight(b.left);
        a.update();

        s.setLeft(b.right);
        s.update();

        b.setLeft(a);
        b.setRight(s);
        b.update();

        return b;

    }

    private Node rotateRightDouble(Node a) {

        Node s = a.left;
        Node b = s.right;

        if (a == root) {
            b.setRoot();
        } else {
            a.parent.replace(a, b);
        }

        a.setLeft(b.right);
        a.update();

        s.setRight(b.left);
        s.update();

        b.setRight(a);
        b.setLeft(s);
        b.update();

        return b;

    }

    @Override
    public String toString() {
        if (root == null) {
            return "[null]";
        } else {
            return root.toString();
        }
    }

}
