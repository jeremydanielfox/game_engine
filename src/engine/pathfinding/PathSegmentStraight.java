package engine.pathfinding;

import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;


/**
 * A path segment for a simple line segment.
 * 
 * @author Kaighn
 */
@Settable
public class PathSegmentStraight implements PathSegment {
    private PointSimple start, end;

    public PathSegmentStraight (PointSimple start, PointSimple end) {
        this.start = new PointSimple(start);
        this.end = new PointSimple(end);
    }

    @Override
    public PointSimple getPoint (double distance) {
        return PointSimple.pointOnLine(start, end, distance);
    }

    private boolean onSegment (PointSimple point) {
        return PointSimple.pointInBetween(start, end, point);
    }

    @Override
    public double getLength () {
        return PointSimple.norm(start, end);
    }

    @Settable
    public void setStart (PointSimple start) {
        this.start = new PointSimple(start);
    }

    @Settable
    public void setEnd (PointSimple end) {
        this.end = new PointSimple(end);
    }

}
