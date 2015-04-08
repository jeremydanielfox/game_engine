package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;

/**
 * A strategy that is used by an object in cartesian space to find its next desired position.
 * @author Kaighn
 *
 */
public interface Mover {
	
	/**
	 * Based on mover's state, returns next destination for moving.
	 * @param current mover's current state
	 * @return point of the destination
	 * @throws EndOfPathException if unit can move no longer
	 */
    public PointSimple move(PointSimple current) throws EndOfPathException;
    
    @Settable
    /**
     * See Movable's speed method.
     * @param speed
     */
    public void setSpeed(double speed);
}