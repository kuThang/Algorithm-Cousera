/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

public class KdTree {
    private Node root;

    private class Node {
        private Node left, right;
        private Point2D point;
        private int level;
        private int count;
        Node(Point2D p, int lv) {
            this.point = p;
            this.left = null;
            this.right = null;
            this.level = lv;
            this.count = 0;
        }
        public int compareTo(Point2D that) {
            // Point2D that = (Point2D) o ;
            double compare = this.level % 2 == 0 ? this.point.x() - that.x() : this.point.y() - that.y();
            if (compare < 0) {
                return -1;
            } else if (compare > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    private Node insert(Node x, Point2D p, int lastLevel) {
        if (x == null) return new Node(p, lastLevel++);

        int compare = x.compareTo(p);
        if (compare > 0) x.right = insert(x.right, p, x.level);
        else if (compare < 0) x.left = insert(x.left, p, x.level);
        else {
            if (x.left == null) x.left = insert(x.left, p, x.level);
            else if (x.right == null) x.right = insert(x.right, p, x.level);
            else {
                if (size(x.left) < size(x.right)) x.left = insert(x.left, p, x.level);
                else x.right = insert(x.right, p, x.level);
            }
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node search(Node x, Point2D p) {
        if (x == null) return null;
        int compare = x.compareTo(p);
        if (compare > 0) return search(x.right, p);
        else if (compare < 0) return search(x.left, p);
        else {
            Node left = search(x.left, p);
            Node right = search(x.right, p);
            if (left != null) return left;
            else if (right != null) return right;
            else return null;
        }
    }

    public KdTree() {

    }

    public boolean isEmpty() { // is the set empty?
        return size(root) == 0;
    }

    public int size() { // number of points in the set
        return size(root);
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        root = insert(root, p, -1);
    }
    public boolean contains(Point2D p) { // does the set contain point p?
        Node node = search(root, p);
        if (node != null) return true;
        else return false;
    }
    public void draw() { // draw all points to standard draw

    }
    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        return new LinkedList<>();
    }
    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        return null;
    }
    public static void main(String[] args) { // unit testing of the methods (optional)

    }
}
