import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Test {

    public static void main(String[] args) {

        Point p1 = new Point(10000,0);
        Point p2 = new Point(0,10000);
        Point p3 = new Point(3000,7000);
        Point p4 = new Point(7000,3000);
        Point p5 = new Point(20000,21000);
        Point p6 = new Point(3000,4000);
        Point p7 = new Point(14000,15000);
        Point p8 = new Point(6000,7000);

        Point[] points = new Point[8];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;
        points[5] = p6;
        points[6] = p7;
        points[7] = p8;

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

