package engine.gameobject;

import engine.fieldsetting.Settable;


/**
 * Mover that moves to a point specified in the constructor.
 *
 * @author Kaighn
 *
 */
@Settable
public abstract class MoverPoint extends BasicMover {
    private PointSimple myPoint;

    public MoverPoint () {
        super(0);
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
