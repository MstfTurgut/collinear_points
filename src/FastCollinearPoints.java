
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {

        if(points == null) {
            throw new IllegalArgumentException("Points array is null");
        }

        if(containsNull(points)) {
            throw new IllegalArgumentException("One of the point in points array is null");
        }

        if(containsDuplicate(points)) {
            throw new IllegalArgumentException("Duplicated entries in given points");
        }

        int N = points.length;
        ArrayList<LineSegment> lsList = new ArrayList<>();
        Point[] copyPoints = Arrays.copyOf(points, N);

        HashSet<Double> slopes = new HashSet<>();

        Arrays.sort(points);

        for(Point p: points) {
            Arrays.sort(copyPoints);
            Arrays.sort(copyPoints, p.slopeOrder());

            // search for adjacency
            // sliding window technique

            HashSet<Double> set = new HashSet<>();

            int windowStart = 0;
            for(int windowEnd = 0; windowEnd < N ; windowEnd++) {

                set.add(p.slopeTo(copyPoints[windowEnd]));
                if (set.size() == 2) {

                    if(windowEnd - windowStart >= 3 && !slopes.contains(p.slopeTo(copyPoints[windowStart]))) {

                        LineSegment ls = new LineSegment(p , copyPoints[windowEnd - 1]);

                        lsList.add(ls);

                        slopes.add(p.slopeTo(copyPoints[windowStart]));
                        set.remove(p.slopeTo(copyPoints[windowStart]));

                        windowStart = windowEnd;
                    }
                    set.remove(p.slopeTo(copyPoints[windowStart++]));

                }


            }

        }

        segments = lsList.toArray(new LineSegment[0]);

    }

    public int numberOfLineSegment() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private boolean containsDuplicate(Point[] points) {
        Arrays.sort(points);
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
