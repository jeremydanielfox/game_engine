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
public class MoverPath extends BasicMover {
	Path myPath;
	
	public MoverPath() {
	    super(0);
	    myPath = new PathFixed();
	}
	
	public MoverPath(Path pf, double speed){
	        super(speed);
		myPath = pf;
	}
	
	 
	 //This switch statement is not worth having polymorphism/using a state pattern.
	 //No incompatible extensions will be made.
	 /**
	  * Moves according to path i.e. returns correct point on the path
	  */
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException{
	        myDistance += currentSpeed();
	        return myPath.getNextLocation(myDistance, currentSpeed(), current);
	}
	
	@Settable
	public void setPath(Path path) {
	    myPath = path;
	}
	
	@Settable
	public void setDistance(double distance) {
	    myDistance = distance;
	}
	
	/**
	 * This sets the mover such that it will pick up on the path the spawner spawned this at. 
	 */
	@Override
	public Mover clone(){
	    MoverPath clone = new MoverPath(myPath, inherentSpeed);
	    clone.myDistance = myDistance;
	    return clone;
	}
	
}
