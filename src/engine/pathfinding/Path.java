package engine.pathfinding;

import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import engine.gameobject.PointSimple;


/**
 * Responsible for telling GameObjects their next position on a grid.
 *
 * @author Kaighn
 */
public interface Path {
    /**
     * Based on a gameobject's current row and column, return its next grid
     * location.
     * 
     * @param current
     *        current point of a gameobject
     * @param distance distance on the path of the point to be queried
     * @return point of the next destination
     * @throws EndOfPathException if no more points exist on the path
     */
    public PointSimple getNextLocation (double distance, double speed, PointSimple current)
                                                                                           throws EndOfPathException;

    /**
     * Adds path segment to the current (collection of) segment.
     * 
     * @param ps path segment to be appended
     */
    public void addPathSegment (PathSegment ps);

    public void updatePath () throws NoPathExistsException;

}
