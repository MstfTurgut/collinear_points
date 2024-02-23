import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lsList;

    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }

        if (containsNull(points)) {
            throw new IllegalArgumentException("One of the point in points array is null");
        }

        Point[] copyPoints = Arrays.copyOf(points, points.length);

        if (containsDuplicate(copyPoints)) {
            throw new IllegalArgumentException("Duplicated entries in given points");
        }

        Arrays.sort(copyPoints);


        int N = copyPoints.length;
        lsList = new ArrayList<>();

        // BRUTE-FORCE WAY TO COMPUTE COLLINEAR POINTS
        for (int i = 0; i < N-3;i++) {
            for (int j = i + 1; j < N-2; j++) {
                for (int k = j + 1; k < N-1; k++) {
                    for (int o = k + 1; o < N; o++) {
                        double slope1 = copyPoints[i].slopeTo(copyPoints[j]);
                        double slope2 = copyPoints[i].slopeTo(copyPoints[k]);
                        double slope3 = copyPoints[i].slopeTo(copyPoints[o]);

                        if (slope1 == slope2 && slope2 == slope3) {
                            LineSegment ls = new LineSegment(copyPoints[i], copyPoints[o]);
                            lsList.add(ls);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lsList.size();
    }

    public LineSegment[] segments() {
        return lsList.toArray(new LineSegment[0]);
    }

    private boolean containsDuplicate(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1;i++) {
            if (points[i].compareTo(points[i+1]) == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNull(Point[] points) {
        for (Point p : points) {
            if (p == null) {
                return true;
            }
        }
        return false;
    }

}
