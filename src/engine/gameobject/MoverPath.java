package engine.gameobject;

import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;

/**
 * A mover that moves according to a pathfinder.
 * @author Kaighn
 *
 */
public class MoverPath implements Mover {
	Path myPath;
	private double myDistance, mySpeed;
	
	public MoverPath(Path pf, double speed){
		myDistance = 0;
		myPath = pf;
		mySpeed = speed;
	}
	
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException {
		myDistance += mySpeed;
		return myPath.getNextLocation(current, myDistance);
	}

	@Override
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
}
