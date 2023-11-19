import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     */
    public double slopeTo(Point that) {

        if(that.y == this.y && that.x == this.x) { // degenerative
            return Double.NEGATIVE_INFINITY;
        } else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY; // vertical line
        } else if (that.y == this.y) {
            return 0.0;
        } else {
            return (double) (that.y - this.y) / (that.x - this.x); // normal cases & horizontal line(slope = 0)
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *         return the value if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        // compare point by y , if y are equal compare x
        if(this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            return Integer.compare(this.x, that.x);
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(slopeTo(p1) , slopeTo(p2));
            }
        };

    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
