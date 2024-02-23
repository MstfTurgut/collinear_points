import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FastCollinearPoints {

    // Data structures for storing line segments and slopes
    private final ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private final HashMap<Double, HashSet<Point>> lines = new HashMap<>();

    /*
     * Constructs a FastCollinearPoints object and finds all collinear line segments.
     */
    public FastCollinearPoints(Point[] points) {

        // Input validation
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }
        if (containsNull(points)) {
            throw new IllegalArgumentException("One of the points in the array is null");
        }
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        if (containsDuplicate(sortedPoints)) {
            throw new IllegalArgumentException("Duplicate entries in given points");
        }

        // Iterate through each point and find collinear segments
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            Point p = sortedPoints[i];

            // Create a copy of the remaining points and sort them by slope with p
            Point[] copyPoints = new Point[points.length - i - 1];
            System.arraycopy(sortedPoints, i + 1, copyPoints, 0, copyPoints.length);
            Arrays.sort(copyPoints, p.slopeOrder());

            // Process the sorted copy array to find collinear segments
            int equalityStrike = 0;
            for (int s = 0; s < copyPoints.length - 1; s++) {
                double slope1 = p.slopeTo(copyPoints[s]);
                double slope2 = p.slopeTo(copyPoints[s + 1]);

                if (slope1 == slope2) {
                    equalityStrike++;
                } else {
                    if (equalityStrike >= 2 && isLineValid(slope1, copyPoints[s])) {
                        LineSegment lineSegment = new LineSegment(p, copyPoints[s]);
                        addLine(slope1, copyPoints[s]);
                        lineSegments.add(lineSegment);
                    }
                    equalityStrike = 0;
                }
            }
            if (equalityStrike >= 2 && isLineValid(p.slopeTo(copyPoints[copyPoints.length - 1]), copyPoints[copyPoints.length - 1])) {
                LineSegment lineSegment = new LineSegment(p, copyPoints[copyPoints.length - 1]);
                addLine(p.slopeTo(copyPoints[copyPoints.length - 1]), copyPoints[copyPoints.length - 1]);
                lineSegments.add(lineSegment);
            }
        }
    }

    private void addLine(double slope, Point endPoint) {
        HashSet<Point> endPoints;
        HashSet<Point> slopeEndPoints = lines.get(slope);
        if(slopeEndPoints != null) endPoints = slopeEndPoints;
        else endPoints = new HashSet<>();
        endPoints.add(endPoint);
        lines.put(slope, endPoints);
    }

    private boolean isLineValid(double slope, Point endPoint) {
        HashSet<Point> endPoints = lines.get(slope);
        if(endPoints == null) return true;
        else return !endPoints.contains(endPoint);
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    private boolean containsDuplicate(Point[] points) {
        for(int i = 0; i < points.length - 1;i++) {
            if(points[i].compareTo(points[i+1]) == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNull(Point[] points) {
        for(Point p : points) {
            if(p == null) {
                return true;
            }
        }
        return false;
    }


}
