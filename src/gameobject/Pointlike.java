package gameobject;

/**
 * Implemented if the class has a cartesian representation of its position.
 * @author Kaighn
 *
 */
public interface Pointlike {
	public double getX();
	public double getY();
	public boolean withinRange(Pointlike point, double range);
}
