package engine.pathfinding;

import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;


/**
 * A PathSegment represented by a Bezier curve of any number of points. The Bezier curve is
 * parametrized
 * for t values between 0 and 1, so this class normalizes the curve to length one.
 * 
 * @author Jeremy
 *
 */
@Settable
public class PathSegmentBezier implements PathSegment {
    private List<PointSimple> myPoints;
    private double myLength;

    /**
     * Creates a new Path Segment that is parametrized as a Bezier Curve. Takes in an ordered list
     * of points that represent the points that parametrize the bezier curve. In this list, the
     * first point represents
     * the start, the last represents the end, and the ones in between represent the ordered control
     * points.
     * Automatically calculates the length of the curve upon creation.
     */
    public PathSegmentBezier (List<PointSimple> points) {
        myPoints = points;
        myLength = approximateLength(points);
    }

    /**
     * Returns a point along the Bezier Curve. Normalizes the distance along the curve my dividing
     * by the curve's length;
     */
    @Override
    public PointSimple getPoint (double distance) {
        return BezierRecurse(myPoints, distance / myLength);
    }

    @Override
    public double getLength () {
        if (myLength == 0)
            myLength = approximateLength(myPoints);
        return myLength;
    }

    /**
     * Recursively defines a Bezier Curve. Allows for a point to be returned given a t value between
     * zero and one, inclusive.
     * Incorrect values will be given if the t value is not between zero and one.
     */
    private PointSimple BezierRecurse (List<PointSimple> points, double t) {
        if (points.size() == 1)
            return points.get(0);
        PointSimple p1 = BezierRecurse(points.subList(0, points.size() - 1), t);
        PointSimple p2 = BezierRecurse(points.subList(1, points.size()), t);
        return p1.multiply(1 - t).add(p2.multiply(t));
    }

    /**
     * Approximates the length of a Bezier curve by calculating 100,000 points along the curve and
     * summing
     * the distances between each.
     */
    private double approximateLength (List<PointSimple> points) {
        double currentLength = 0;
        PointSimple currentPoint = points.get(0);
        PointSimple nextPoint;
        for (int i = 0; i < 1; i += .00001) {
            nextPoint = BezierRecurse(points, i);
            currentLength += PointSimple.distance(currentPoint, nextPoint);
            currentPoint = nextPoint;
        }
        return currentLength;
    }
    @Settable
    public void setPoints(List<PointSimple> points) {
        myPoints = points;
    }

}
