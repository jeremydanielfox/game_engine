package engine.pathfinding;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.grid.GridCell;
import javafx.geometry.Point2D;

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
	 *            current point of a gameobject
	 * @param distance distance on the path of the point to be queried
	 * @return point of the next destination
	 * @throws EndOfPathException if no more points exist on the path
	 */
	public PointSimple getNextLocation(PointSimple current, double distance)
			throws EndOfPathException;

	/**
	 * Adds path segment to the current (collection of) segment.
	 * @param ps path segment to be appended
	 */
	public void addPathSegment(PathSegment ps);

}
