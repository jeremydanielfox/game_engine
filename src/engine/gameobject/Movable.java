package engine.gameobject;

import engine.pathfinding.EndOfPathException;


/**
 * Implemented by a class if it can update its own position.
 * 
 * @author Kaighn
 *
 */
public interface Movable {
	
	/**
	 * Moves itself to it's next position.
	 * @throws EndOfPathException if unit can no longer be moved.
	 */
    public void move () throws EndOfPathException;

    /**
     * Sets speed, the amount by which a unit moves per turn.
     * @param speed 
     */
    public void setSpeed (double speed);
}
