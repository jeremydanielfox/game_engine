package engine.gameobject.movers;

import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;


/**
 * Mover that moves to a point specified in the constructor.
 *
 * @author Kaighn
 *
 */
@Settable
public abstract class MoverPoint extends BasicMover {
    private PointSimple myPoint;
    private static final int INITIAL_SPEED = 0;
    public MoverPoint () {
        super(INITIAL_SPEED);
        myPoint = new PointSimple();
    }

    public MoverPoint (PointSimple point, double speed) {
        super(speed);
        myPoint = point;
    }

    /**
     * Sets the mover's reference point to point
     * 
     * @param point
     */
    @Settable
    public void setPoint (PointSimple point) {
        myPoint = point;
    }

    protected PointSimple getPoint () {
        return myPoint;
    }
}
