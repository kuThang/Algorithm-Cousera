/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.LinkedList;

public class BinarySearchTree<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node implements Comparable<Key> {
        Key key;
        Value val;
        int count;
        private Node left, right;
        Node(Key k, Value v, int count) {
            this.key = k;
            this.val = v;
            this.count = count;
            this.right = null;
            this.left = null;
        }

        @Override
        public int compareTo(Key o) {
            return this.key.compareTo(o);
        }
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        int compare = key.compareTo(node.key);
        if(compare < 0) return rank(node.left, key);
        else if (compare > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }

    /**
     * How many keys <= k
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, 1);

        int compare = key.compareTo(node.key);
        if(compare < 0) node.left = put(node.left, key, val);
        else if (compare > 0) node.right = put(node.right, key, val);
        else node.val = val;
        node.count = 1 + size(node.left) + size(node.right);
        // System.out.println("-- put key " + key + ", val = " + val + ", count at key " + node.key + " = " + node.count);
        return node;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node floor(Node node, Key key) {
        if(node == null) return null;
        int compare = key.compareTo(node.key);
        if(compare < 0) return floor(node.left, key);
        else if (compare > 0) {
            Node fr = floor(node.right, key);
            if(fr != null) return fr;
            else return node;
        }
        else return node;

    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if ( node == null ) return null;
        else return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if(node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            Node left = ceiling(node.left, key);
            if (left != null) return left;
            else return node;
        } else if (compare > 0) {
            return ceiling(node.right, key);
        } else {
            return node;
        }
    }

     /**
     * How many keys >= k
     */
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) return null;
        else return node.key;
    }

    public Value get(Key key) {
        Node node = root;
        while(node != null) {
            int compare = key.compareTo(node.key);
            if(compare < 0) node = node.left;
            else if (compare > 0) node = node.right;
            else return node.val;
        }

        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    public int size() {
        return size(root);
    }

    private void inorder(Node node, LinkedList<Key> ll){
        if (node == null) return;
        inorder(node.left, ll);
        ll.add(node.key);
        inorder(node.right, ll);
    }

    public Iterable<Key> iterator() {
        LinkedList<Key> ll = new LinkedList<>();
        inorder(root, ll);
        return ll;
    }

    private Node min(Node node) {
        if(node == null) throw new NoSuchElementException();
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node max(Node node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node delMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;

        node.left = delMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Key delMin() {
        Node min = min(root);
        if (min == null) return null;
        root = delMin(root);
        return min.key;
    }

    private Node delMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node.left;

        node.right = delMin(node.right);
        node.count = 1 + size(node.right) + size(node.left);
        return node;
    }

    public Key delMax() {
        Node max = max(root);
        if (max == null) return null;
        root = delMax(root);
        return max.key;
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = delete(node.left, key);
        } else if (compare > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node delNode = node;
            node = min(node.right);
            node.right = delMin(node.right);
            node.left = delNode.left;
        }

        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Key getMin() {
        if(root == null) throw new NoSuchElementException();
        Node min = min(root);
        if (min != null) return min.key;
        else return null;
    }

    public Key getMax() {
        if(root == null) throw new NoSuchElementException();
        Node max = max(root);
        if (max != null) return max.key;
        else return null;
    }

    public BinarySearchTree() {

    }
    public static void main(String[] args) {
        int n = 1000000;
        int[] array = StdRandom.permutation(n);
        BinarySearchTree bst = new BinarySearchTree();
        Stopwatch stw = new Stopwatch();
        int k = 0;
        for (int i : array) {
            // System.out.println("bst[" + i + "] is " + k );
            if(i == 894377) {
                k ++;
                continue;
            }
            bst.put(i, k++);
            // if (k  > 1000 && k < 1010) System.out.println(k);
        }
        System.out.println("BST with n = " + n + " put value in second \t\t" + stw.elapsedTime());

        // stw = new Stopwatch();
        // k = 0;
        // for (int i : array) {
        //     int x = (int)bst.get(i);
        //     k ++;
        //     // if (k  > 1000 && k < 1010) System.out.println(x);
        // }
        // System.out.println("BST with n = " + n + " get value in second \t\t" + stw.elapsedTime());

        stw = new Stopwatch();
        System.out.println("Rank of 894377 is " + bst.rank(894377));
        System.out.println("Floor of 894377 is " + bst.floor(894377));
        System.out.println("Ceiling of 894377 is " + bst.ceiling(894377));
        System.out.println(bst.size());
        System.out.println("BST rank with n = " + n + " get value in second \t\t" + stw.elapsedTime());

        for (int i = 0; i < 10; i++) {
            System.out.println("Del min = " + bst.delMin() + ", current Min = " + bst.getMin() + ", size bst[11] = " + bst.rank(11));
        }

        for (int i = 10; i < 20; i++) {
            bst.delete(i);
            System.out.println("Del key = " + i + ", then current Min = " + bst.getMin() + ", size bst[15] = " + bst.rank(15));
        }

        LinkedList ll = (LinkedList) bst.iterator();
        for (int i = 0; i < 10; i++) {
            System.out.println("Linkedlist = " + ll.get(i));
        }
    }
}
