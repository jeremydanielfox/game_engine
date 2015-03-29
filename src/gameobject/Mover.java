package gameobject;

/**
 * A strategy that is used by an object in cartesian space to find its next desired position.
 * @author Kaighn
 *
 */
public interface Mover {
    public Pointlike move(double x, double y);
}
