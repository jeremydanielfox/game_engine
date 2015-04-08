package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


/**
 * Mover that moves to a point specified in the constructor.
 * 
 * @author Kaighn
 *
 */
@Settable
public class MoverPoint implements Mover {
    private double mySpeed;
    private PointSimple myPoint;

    public MoverPoint () {
        mySpeed = 0;
        myPoint = new PointSimple();
    }

    public MoverPoint (PointSimple point, double speed) {
        mySpeed = speed;
        myPoint = point;
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        PointSimple p = PointSimple.pointOnLine(current, myPoint, mySpeed);
        if (PointSimple.pointInBetween(current, myPoint, p)) {
            throw new EndOfPathException();
        }
        return p;
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    @Settable
    public void setPoint (PointSimple point) {
        myPoint = point;
    }

    public Mover clone () {
        return new MoverPoint(myPoint, mySpeed);
    }
}
