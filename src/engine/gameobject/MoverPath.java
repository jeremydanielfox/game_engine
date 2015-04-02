package engine.gameobject;

import engine.pathfinding.EndOfPathException;
import engine.pathfinding.PathFinder;

/**
 * A mover that moves according to a pathfinder.
 * @author Kaighn
 *
 */
public class MoverPath implements Mover {
	PathFinder myPathFinder;
	private double myDistance, mySpeed;
	
	public MoverPath(PathFinder pf, double speed){
		myDistance = 0;
		myPathFinder = pf;
		mySpeed = speed;
	}
	
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException {
		myDistance += mySpeed;
		return myPathFinder.getNextLocation(current, myDistance);
	}

	@Override
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
}
