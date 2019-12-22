/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

public class KdTree {
    private static final int VERTICAL = 0;
    private static final int HORIZONTAL = 1;
    private Node root;

    private class NearestNode {
        private double distance;
        private Node node;

        NearestNode(Node n, double dis) {
            distance = dis;
            node = n;
        }
    }

    private class Node {
        private Node left, right;
        private final Point2D point;
        private final int level;
        private int count;
        private Node parent;
        private final RectHV rect; // all points in this branch ( this node and its subtree )
                                   // are located in this rect ( limit by parent node )

        Node(Point2D p, int lv, Node parent) {
            this.point = p;
            this.left = null;
            this.right = null;
            this.level = lv % 2;
            this.count = 1;
            this.parent = parent;
            if (parent == null) this.rect = new RectHV(0, 0, 1, 1);
            else {
                double xmin = parent.rect.xmin();
                double xmax = parent.rect.xmax();
                double ymin = parent.rect.ymin();
                double ymax = parent.rect.ymax();
                // one param from (xmin, ymin, xmax, ymax) is limited by its parent
                int compare = parent.compareTo(p);
                if (this.level == VERTICAL) {
                    if (compare > 0) ymax = parent.point.y();
                    else ymin = parent.point.y();
                } else {
                    if (compare > 0) xmax = parent.point.x();
                    else xmin = parent.point.x();
                }
                this.rect = new RectHV(xmin, ymin, xmax, ymax);
            }
        }

        public int compareTo(Point2D that) {
            if (this.level == VERTICAL) {
                if (this.point.x() - that.x() > 0) return 1;
                if (this.point.x() - that.x() < 0) return -1;
                if (this.point.y() - that.y() > 0) return 1;
                if (this.point.y() - that.y() < 0) return -1;
            }
            if (this.level == HORIZONTAL) {
                if (this.point.y() - that.y() > 0) return 1;
                if (this.point.y() - that.y() < 0) return -1;
                if (this.point.x() - that.x() > 0) return 1;
                if (this.point.x() - that.x() < 0) return -1;
            }
            return 0;
        }

        /*
        Return distance from node's rectangle to one point
        If rectangle include point, return -1
        If xmin <= point.x <= xmax, return min(
         */
        public double hDistance(Point2D that) {
            double hDistance;
            if(this.rect.contains(that)) {
                hDistance = -1;
            } else {
                hDistance = this.rect.distanceTo(that);
            }
            return hDistance;
        }
    }

    private NearestNode nearest(Node node, Point2D p, NearestNode best) {
        if (node == null) return best;

        if (best != null && best.node != null
                && best.node.point.distanceTo(p) <= node.hDistance(p)) {
            // the distance from p to best node is less than the distance from p to current node's rect
            // there is no chance that any node in this branch can beat the best one
            return best;
        }

        if (node.point.distanceSquaredTo(p) < best.distance) {
            best.node = node;
            best.distance = node.point.distanceSquaredTo(p);
        }
        int compare = node.compareTo(p);
        if (compare > 0) {
            nearest(node.left, p, best);
            nearest(node.right, p, best);
        } else if (compare < 0) {
            nearest(node.right, p, best);
            nearest(node.left, p, best);
        }
        return best;
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    private Node insert(Node node, Point2D p, int lastLevel, Node parent) {
        if (node == null) return new Node(p, lastLevel + 1, parent);

        int compare = node.compareTo(p);
        if (compare > 0) node.left = insert(node.left, p, node.level, node);
        else if (compare < 0) node.right = insert(node.right, p, node.level, node);
        // else {
        //     if (node.level == VERTICAL) {
        //         if (node.point.y() - p.y() > 0) node.left = insert(node.left, p, node.level);
        //         if (node.point.y() - p.y() < 0) node.right = insert(node.right, p, node.level);
        //     } else {
        //         if (node.point.x() - p.x() > 0) node.left = insert(node.left, p, node.level);
        //         if (node.point.x() - p.x() < 0) node.right = insert(node.right, p, node.level);
        //     }
        // }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node search(Node node, Point2D p) {
        if (node == null) return null;
        int compare = node.compareTo(p);
        if (compare > 0) return search(node.left, p);
        else if (compare < 0) return search(node.right, p);
        else {
            // if (node.level == VERTICAL) {
            //     if (node.point.y() - p.y() > 0) return search(node.left, p);
            //     if (node.point.y() - p.y() < 0) return search(node.right, p);
            // } else {
            //     if (node.point.x() - p.x() > 0) return search(node.left, p);
            //     if (node.point.x() - p.x() < 0) return search(node.right, p);
            // }
            return node;
        }
    }

    private void draw(Node x) {
        if (x == null) return;
        x.point.draw();
        draw(x.left);
        draw(x.right);
    }

    // // smallest item that larger than p
    // private Node ceiling(Node x, Point2D p) {
    //     if (x == null) return null;
    //     int compare = x.compareTo(p);
    //     if (compare < 0) {
    //         return ceiling(x.right, p);
    //     } else if (compare > 0) {
    //         Node left = ceiling(x.left, p);
    //         if (left == null) return x;
    //         else return left;
    //     } else {
    //         return x;
    //     }
    // }
    //
    // // largest item that smaller than p
    // private Node floor(Node x, Point2D p) {
    //     if (x == null) return null;
    //     int compare = x.compareTo(p);
    //     if (compare < 0) {
    //         Node right = floor(x.right, p);
    //         if (right == null) return x;
    //         else return right;
    //     } else if (compare > 0) {
    //         return floor(x.right, p);
    //     } else {
    //         return x;
    //     }
    // }

    public KdTree() {

    }

    public boolean isEmpty() { // is the set empty?
        return size(root) == 0;
    }

    public int size() { // number of points in the set
        return size(root);
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p, -1, null);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null) throw new IllegalArgumentException();
        Node node = search(root, p);
        if (node != null) return true;
        else return false;
    }

    public void draw() { // draw all points to standard draw
        draw(root);
    }

    private void range(Node node, RectHV rect, LinkedList<Point2D> listPoints) {
        if (node == null) return;

        if (rect.contains(node.point)) {
            listPoints.add(node.point);
        }

        if (node.level == VERTICAL) {
            if (node.point.x() >= rect.xmin()) {
                range(node.left, rect, listPoints);
            }
            if (node.point.x() <= rect.xmax()) {
                range(node.right, rect, listPoints);
            }
        } else {
            if (node.point.y() >= rect.ymin()) {
                range(node.left, rect, listPoints);
            }
            if (node.point.y() <= rect.ymax()) {
                range(node.right, rect, listPoints);
            }
        }
        // int sum = node.compareTo(new Point2D(rect.xmin(), rect.ymin()))
        //         + node.compareTo(new Point2D(rect.xmax(), rect.ymax()));
        // if (sum <= 0) {
        //     LinkedList<Point2D> subList = range(node.right, rect);
        //     if (subList != null) listPoints.addAll(subList);
        // }
        // if (sum >= 0) {
        //     LinkedList<Point2D> subList = range(node.left, rect);
        //     if (subList != null) listPoints.addAll(subList);
        // }
        // return listPoints;
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();
        LinkedList<Point2D> listPoints = new LinkedList<>();
        range(root, rect, listPoints);
        return listPoints;
    }

    private boolean isSameNode(Node a, Node b) {
        if (a == null || b == null) return false;
        return a.point.equals(b.point);
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        NearestNode nearestNode = nearest(root, p, new NearestNode(null, Double.POSITIVE_INFINITY));
        if (nearestNode == null || nearestNode.node == null) return null;
        else return nearestNode.node.point;
    }

    public static void main(String[] args) { // unit testing of the methods (optional)

    }
}
