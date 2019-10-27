/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    Point [] pts;
    int segmentCount = 0;

    private class LineStack{
        // private
    }
    private class LineNode{
        // public
    }
    LineSegment [] segs = new LineSegment[1];
    boolean [] inLine;

    private void exchange(int i, int j) {
        Point temp = pts[i];
        pts[i] = pts[j];
        pts[j] = temp;
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException();
        }
        pts = new Point[points.length];
        inLine = new boolean[points.length];
        int minY = 0;
        int minIdx = -1;
        int maxY = Integer.MAX_VALUE;
        int maxIdx = -1;

        Point [] sorted = new Point[4];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            sorted[i] = points[i];
            int currentIndex = i;
            for (int j = i; j >= 1; j++) {
                int compare = sorted[currentIndex].compareTo(sorted[j-1]);
                if (compare == 0) {
                    throw new IllegalArgumentException();
                } else if (compare < 0) {
                    exchange(currentIndex, j-1);
                    currentIndex = j-1;
                }
            }
        }

        double slope = points[0].slopeTo(points[1]);
        if (slope == points[0].slopeTo(points[2]) && slope == points[0].slopeTo(points[2])) {
            segs[0] = new LineSegment(new Point(10000, 0), new Point(0, 10000));
        }

        boolean checked = false;


        for (int i = 0; i < points.length; i++) {
            checked = false;
            if (points[i] == null)
                throw new IllegalArgumentException();

            for (int j = i; j >= 1; j--) {
                int compare = points[j].compareTo(points[j-1]);
                if (compare == 0)
                    throw new IllegalArgumentException(); // same point
                else if (compare < 0) {

                }

                if (i == 1) {
                    slope = points[i].slopeTo(points[j]);
                } else {
                    if (!checked && slope != points[i].slopeTo(points[j])) {
                        return;
                    } else {
                        checked = true;
                    }
                }
            }
        }
        // computeLines();
        // segs[0] = new LineSegment(new Point(10000, 0), new Point(0, 10000));
    }    // finds all line segments containing 4 points

    private void computeLines() {
        for (int i = 0; i < pts.length; i++) {
            double [] slopes = new double[pts.length];
            for (int j = 0; j < pts.length; j++) {
                slopes[i] = pts[i].slopeTo(pts[j]);
            }
            
        }
    }

    public int numberOfSegments() {
        return segmentCount;
    }        // the number of line segments

    public LineSegment[] segments()  {
        return segs;
    }    // the line segments

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            System.out.println(x);
            System.out.println(y);
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
