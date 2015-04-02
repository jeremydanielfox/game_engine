package engine.pathfinding;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.grid.GridCell;
import javafx.geometry.Point2D;

/**
 * Responsible for telling GameObjects their next position on a grid.
 * @author Kaighn  
 */
public interface PathFinder {
	/**
	 * Based on a gameobject's current row and column, return its next grid location.
	 * @throws EndOfPathException 
	 */
	public PointSimple getNextLocation(PointSimple current, double distance) throws EndOfPathException;

    public void addPathSegment(PathSegment ps);

}
