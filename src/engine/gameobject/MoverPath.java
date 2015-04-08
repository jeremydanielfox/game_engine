package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;
import engine.pathfinding.PathFixed;

/**
 * A mover that moves according to a pathfinder.
 * @author Kaighn
 *
 */
@Settable
public class MoverPath implements Mover {
	Path myPath;
	private double myDistance, mySpeed;
	
	public MoverPath() {
	    myPath = new PathFixed();
	    myDistance = 0;
	    mySpeed = 0;
	}
	
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
	@Settable
	@Override
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
	
	@Settable
	public void setPath(Path path) {
	    myPath = path;
	}
	
	@Settable
	public void setDistance(double distance) {
	    myDistance = distance;
	}
	
}
