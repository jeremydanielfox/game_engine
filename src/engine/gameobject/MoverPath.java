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
	        if (frozen != true)
	            myDistance += currentSpeed();
	        return myPath.getNextLocation(myDistance, current);
	}
	
	@Settable
	public void setPath(Path path) {
	    myPath = path;
	}
	
	@Settable
	public void setDistance(double distance) {
	    myDistance = distance;
	}
	
	public Mover clone(){
	    return new MoverPath(myPath, inherentSpeed);
	}
	
}
