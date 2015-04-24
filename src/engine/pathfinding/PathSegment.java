package engine.pathfinding;

import engine.gameobject.PointSimple;


/**
 * Represent a single piece of a fixed path. This piece should follow the same mathematical
 * rules. Ie: line, curve, etc. But no mixing of any two.
 * 
 * @author Kaighn
 *
 */
public interface PathSegment {
    /**
     * Returns the point along the segment at the specified distance.
     * 
     * @param distance distance along the path
     * @return the point at the distance distance along the path
     */
    public PointSimple getPoint (double distance);

    /**
     * Each path segment has a length (arc length).
     * 
     * @return arc length of the path
     */
    public double getLength ();
}
