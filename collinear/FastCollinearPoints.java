/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    // Point [] pts;
    private int lineCount = 0;
    private LineSegment [] lineSegments = new LineSegment[1];

    private void exchange(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }



    private void computeLines(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();

            double[] slopes = new double[points.length - 1 - i];

            for (int j = i + 1; j < points.length; j++) {
                double slope = points[i].slopeTo(points[j]);
                if(slope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                slopes[j - i - 1] = points[i].slopeTo(points[j]);

            }

        }
        return;
    }

    public FastCollinearPoints(Point[] points) {

        if (points == null || points.length == 0) {
            throw new IllegalArgumentException();
        }
        if(points.length < 4) {
            return;
        }

        computeLines(points);

        // for (int i = 0; i < points.length; i++) {
        //     if (points[i] == null)
        //         throw new IllegalArgumentException();
        //     for (int j = i; j >= 1; j--) {
        //         int compare = points[j].compareTo(points[j-1]);
        //         if (compare == 0) {
        //             throw new IllegalArgumentException();
        //         } else if (compare < 0) {
        //             exchange(points, j, j-1);
        //         }
        //     }
        // }
        //
        // for (int i = 0; i < points.length - 3; i++) {
        //     for (int j = i + 1; j < points.length - 2; j++) {
        //         for (int x = j + 1; x < points.length - 1; x++) {
        //             for(int y = x + 1; y < points.length; y ++) {
        //                 computeLines(points[i], points[j], points[x], points[y]);
        //             }
        //         }
        //     }
        // }
    }     // finds all line segments containing 4 or more points


    public int numberOfSegments() {
        return lineCount;
    }        // the number of line segments
    public LineSegment[] segments() {
        LineSegment [] result = new LineSegment[lineCount];
        for (int i = 0; i < lineCount; i++) {
            result[i] = lineSegments[i];
        }
        return result;
    }

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // System.out.println("number of line " + collinear.lineCount);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
