/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Point [] pts;
    private int lineCount = 0;
    private LineSegment [] lineSegments = new LineSegment[1];

    private void exchange(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException();
        }
        if(points.length < 4) {
            return;
        }
        pts = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            pts[i] = points[i];
            for (int j = i; j >= 1; j--) {
                int compare = pts[j].compareTo(pts[j-1]);
                if (compare == 0) {
                    throw new IllegalArgumentException();
                } else if (compare < 0) {
                    exchange(pts, j, j-1);
                }
            }
        }

        for (int i = 0; i < pts.length - 3; i++) {
            for (int j = i + 1; j < pts.length - 2; j++) {
                for (int x = j + 1; x < pts.length - 1; x++) {
                    for(int y = x + 1; y < pts.length; y ++) {
                        computeLines(pts[i], pts[j], pts[x], pts[y]);
                    }
                }
            }
        }

    }    // finds all line segments containing 4 points

    private void computeLines(Point pt1, Point pt2, Point pt3, Point pt4) {
        double slope = pt1.slopeTo(pt2);
        if (slope ==  pt1.slopeTo(pt3) && slope ==  pt1.slopeTo(pt4)) {
            if(lineCount == lineSegments.length) {
                LineSegment[] temp = lineSegments;
                lineSegments = new LineSegment[lineCount * 2];
                for (int i=0; i < temp.length; i++) {
                    lineSegments[i] = temp[i];
                }
            }
            lineSegments[lineCount++] = new LineSegment(pt1, pt4);
        }
    }

    public int numberOfSegments() {
        return lineCount;
    }        // the number of line segments

    public LineSegment[] segments()  {
        LineSegment [] result = new LineSegment[lineCount];
        for (int i = 0; i < lineCount; i++) {
            result[i] = lineSegments[i];
        }
        return result;
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
        // System.out.println("number of line " + collinear.lineCount);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
