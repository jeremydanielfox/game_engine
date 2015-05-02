package engine.gameobject.movers;

import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;
import engine.pathfinding.PathFixed;


/**
 * A mover that moves according to a pathfinder.
 * 
 * @author Kaighn
 *
 */
@Settable
public class MoverPath extends BasicMover {
    private Path myPath;
    private static final int INITIAL_SPEED = 1;

    public MoverPath () {
        super(INITIAL_SPEED);
        this.setSpeed(INITIAL_SPEED);
        myPath = new PathFixed();
    }

    public MoverPath (Path path, double speed) {
        setSpeed(speed);
        myPath = path;
    }

    /**
     * Moves according to path i.e. returns correct point on the path
     */
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        myDistance += currentSpeed();
        return myPath.getNextLocation(myDistance, currentSpeed(), current);
    }

    @Settable
    public void setPath (Path path) {
        myPath = path;
    }

    @Settable
    public void setDistance (double distance) {
        myDistance = distance;
    }

    /**
     * This sets the mover such that it will pick up on the path the spawner spawned this at.
     */
    @Override
    public Mover clone () {
        MoverPath clone = new MoverPath(myPath, inherentSpeed);
        clone.myDistance = myDistance;
        return clone;
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        super.setSpeed(speed);
    }

}
