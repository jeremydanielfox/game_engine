package engine.gameobject;

import engine.pathfinding.EndOfPathException;

/**
 * Implemented by a class if it can update its own position.
 * @author Kaighn
 *
 */
public interface Movable {
	public void move() throws EndOfPathException;
	public void setSpeed(double speed);
}
