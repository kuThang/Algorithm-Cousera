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
    // private LineSegment [] lineSegments = new LineSegment[1];
    private double [] lineSlopes = new double[1];
    private Point [] startPoint = new Point[1];
    private Point [] endPoint = new Point[1];

    private void swap(int[] slopes, int i, int j) {
        int temp = slopes[i];
        slopes[i] = slopes[j];
        slopes[j] = temp;
    }

    private void addLine(double slope, Point[] points, int base, int[] idx, int start, int end) {
        int min = base;
        int max = base;
        for (int i = start; i < end; i++) {
            if (points[min].compareTo(points[idx[i]]) < 0) {
                min = idx[i];
            }
            if (points[max].compareTo(points[idx[i]]) > 0) {
                max = idx[i];
            }
        }

        for (int i=0; i < lineCount; i++) {
            if (slope == lineSlopes[i]) {
                if (startPoint[i].compareTo(points[min]) < 0) {
                    startPoint[i] = points[min];
                }
                if (endPoint[i].compareTo(points[max]) > 0) {
                    endPoint[i] = points[max];
                }
                return;
            }
        }

        if(lineCount == startPoint.length) {
            Point[] temp1 = startPoint;
            Point[] temp2 = endPoint;
            double [] tempSlopes = lineSlopes;
            startPoint = new Point[lineCount * 2];
            endPoint = new Point[lineCount * 2];
            lineSlopes = new double[lineCount * 2];
            for (int i=0; i < temp1.length; i++) {
                startPoint[i] = temp1[i];
                endPoint[i] = temp2[i];
                lineSlopes[i] = tempSlopes[i];
            }
        }
        startPoint[lineCount] = points[min];
        endPoint[lineCount] = points[max];
        lineSlopes[lineCount] = slope;
        lineCount++;
    }

    private void swap(double[] slopes, int i, int j) {
        double temp = slopes[i];
        slopes[i] = slopes[j];
        slopes[j] = temp;
    }

    private void computeLines(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();

            double[] slopes = new double[points.length - 1 - i];
            int[] idx = new int [points.length - 1 - i];

            for (int j = i + 1; j < points.length; j++) {
                double slope = points[i].slopeTo(points[j]);
                if(slope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                slopes[j - i - 1] = points[i].slopeTo(points[j]);
                idx[j - i - 1] = j;

                for(int k = j - i - 1; k >= 1; k--) {
                    if (slopes[k] < slopes[k-1]) {
                        swap(slopes, k, k-1);
                        swap(idx, k, k-1);
                    } else
                        break;
                }
            }

            double lastScore = Double.NEGATIVE_INFINITY;
            int countSame = 0;
            int start = 0;
            for (int k = 0; k < slopes.length; k++) {
                if(lastScore != slopes[k]) {
                    if ( countSame >= 3 ) {
                        addLine(lastScore, points, i, idx, start, k);
                    }
                    countSame = 1;
                    start = k;
                    lastScore = slopes[k];

                } else {
                    countSame ++;
                }
            }
            if ( countSame >= 3 ) {
                addLine(lastScore, points, i, idx, start, idx.length);
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
    }


    public int numberOfSegments() {
        return lineCount;
    }

    public LineSegment[] segments() {
        if(lineCount == 0) {
            return new LineSegment[0];
        }

        LineSegment [] lineSegments = new LineSegment[lineCount];
        for (int i = 0; i < lineCount; i++) {
            lineSegments[i] = new LineSegment(startPoint[i], endPoint[i]);
        }
        return lineSegments;
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
