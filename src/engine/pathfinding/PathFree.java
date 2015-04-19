package engine.pathfinding;

import engine.gameobject.PointSimple;

/**
 * A path to represent a free path. Game objects will receive
 * their next destination not through some lookup of preset paths (ie fixed path)
 * but will use pathfinding to navigate obstacles.
 * @author Kaighn
 *
 */
public class PathFree implements Path {

	@Override
	public PointSimple getNextLocation(double distance)
			throws EndOfPathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPathSegment(PathSegment ps) {
		// TODO Auto-generated method stub
		
	}

}
