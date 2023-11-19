
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        if(points == null) {
            throw new IllegalArgumentException("Points array is null");
        }

        if(containsNull(points)) {
            throw new IllegalArgumentException("One of the point in points array is null");
        }

        Arrays.sort(points);

        if(containsDuplicate(points)) {
            throw new IllegalArgumentException("Duplicated entries in given points");
        }

        int N = points.length;
        ArrayList<LineSegment> lsList = new ArrayList<>();
        HashSet<Double> slopes = new HashSet<>();

        for(int i = 0; i < N-3;i++) {
            for(int j = i + 1; j < N-2; j++) {
                for(int k = j + 1; k < N-1; k++) {
                    for(int o = k + 1; o < N; o++) {
                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[o]);

                        if(slope1 == slope2 && slope2 == slope3) {
                            LineSegment ls = new LineSegment(points[i], points[o]);
                            if(!slopes.contains(slope1)) {
                                lsList.add(ls);
                                slopes.add(slope1);
                            }
                        }
                    }
                }
            }
        }
        segments = lsList.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
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
