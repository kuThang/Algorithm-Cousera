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
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();

            pts[i] = points[i];
            for (int j = i; j >= 0; j--) {
                int compare = pts[j].compareTo(pts[j-1]);
                if (compare == 0)
                    throw new IllegalArgumentException();
                else if (compare < 0) {
                    exchange(j, j-1);
                } else
                    break;
            }
        }
        computeLines();
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
