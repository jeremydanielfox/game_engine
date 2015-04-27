package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


/**
 * A strategy that is used by an object in cartesian space to find its next desired position.
 *
 * @author Kaighn
 *
 */
public interface Mover {

    /**
     * Based on mover's state, returns next destination for moving.
     *
     * @param current mover's current state
     * @return point of the destination
     * @throws EndOfPathException if unit can move no longer
     */
    public PointSimple move (PointSimple current) throws EndOfPathException;

    @Settable
    public void setSpeed (double speed);

    /**
    * Speeds/slows down the mover
    * @param percentage i.e. 90 for 90% speedup
    */
   public void speedBuff (double percentage);
      
    public double getSpeed();
    public Mover clone ();

}
