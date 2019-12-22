/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> pointSet;
    public PointSET() { // construct an empty set of points
        pointSet = new TreeSet<>();
    }

    public boolean isEmpty() { // is the set empty?
        return size() == 0;
    }

    public int size() { // number of points in the set
        return pointSet.size();

    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException();
        pointSet.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null || pointSet == null) throw new IllegalArgumentException();
        return pointSet.contains(p);
    }

    public void draw() { // draw all points to standard draw
        if (pointSet == null) return;
        for (Point2D p : pointSet) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();
        if (pointSet == null) return null;
        LinkedList<Point2D> listPoints = new LinkedList<>();
        Point2D topLeft = new Point2D(rect.xmin(), rect.ymin());
        Point2D bottomRight = new Point2D(rect.xmax(), rect.ymax());
        Point2D ceilingKey = pointSet.ceiling(topLeft);
        Point2D floorKey = pointSet.floor(bottomRight);
        if (ceilingKey == null || floorKey == null) return listPoints;
        for (Point2D p : pointSet) {
            if (p.compareTo(ceilingKey) >= 0 && p.compareTo(floorKey) <= 0 && rect.contains(p)) {
                listPoints.add(p);
            }
        }
        return listPoints;
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        if (pointSet == null) return null;
        double bestDistance = Double.POSITIVE_INFINITY;
        Point2D best = null;
        for (Point2D point : pointSet) {
            double distanceSquared = p.distanceSquaredTo(point);
            if (distanceSquared < bestDistance) {
                bestDistance = distanceSquared;
                best = point;
            }
        }

        return best;
    }

    public static void main(String[] args) { // unit testing of the methods (optional)
        // SET<String> set = new SET<String>();
        // StdOut.println("set = " + set);
        //
        // // insert some keys
        // set.add("www.cs.princeton.edu");
        // set.add("www.cs.princeton.edu");    // overwrite old value
        // set.add("www.princeton.edu");
        // set.add("www.math.princeton.edu");
        // set.add("www.yale.edu");
        // set.add("www.amazon.com");
        // set.add("www.simpsons.com");
        // set.add("www.stanford.edu");
        // set.add("www.google.com");
        // set.add("www.ibm.com");
        // set.add("www.apple.com");
        // set.add("www.slashdot.com");
        // set.add("www.whitehouse.gov");
        // set.add("www.espn.com");
        // set.add("www.snopes.com");
        // set.add("www.movies.com");
        // set.add("www.cnn.com");
        // set.add("www.iitb.ac.in");
        //
        //
        // StdOut.println(set.contains("www.cs.princeton.edu"));
        // StdOut.println(!set.contains("www.harvardsucks.com"));
        // StdOut.println(set.contains("www.simpsons.com"));
        // StdOut.println();
        //
        // StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        // StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        // StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        // StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        // StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        // StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        // StdOut.println();
        //
        // StdOut.println("set = " + set);
        // StdOut.println();
        //
        // // print out all keys in this set in lexicographic order
        // for (String s : set) {
        //     StdOut.println(s);
        // }

        // TreeSet<Point2D> test = new TreeSet<>();
    }
}
