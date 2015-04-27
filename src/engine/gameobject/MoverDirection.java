package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


@Settable
public class MoverDirection extends MoverPoint {
    private double distanceLimit;
    private double distanceTraveled;
    private boolean pastPoint;
    
    public MoverDirection()     {
        this(new PointSimple(0,0), 1, 10);
    }

    /**
     * Makes a mover that will move with set speed for length distanceLimit towards direction.
     * 
     * @param direction
     * @param speed
     * @param distanceLimit
     */
    public MoverDirection (PointSimple direction, double speed, double distanceLimit) {
        super(direction, speed);
        this.distanceLimit = distanceLimit;
        distanceTraveled = 0;
        pastPoint = false;
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        distanceTraveled += currentSpeed();
        if (distanceTraveled > distanceLimit) {
            throw new EndOfPathException();
        }
        // This switch statement is here to determine whether to find the point TOWARDS or AWAY FROM
        // the reference.
        PointSimple newPoint = new PointSimple();
        if (!pastPoint) {
            newPoint = PointSimple.pointOnLine(current, getPoint(), currentSpeed());
        }
        else {
            newPoint = PointSimple.pointOnLine(current, getPoint(), -currentSpeed());
        }
        // Accounting for the exact movement that puts the mover past the reference point
        if (currentSpeed() > PointSimple.distance(current, getPoint()) && !pastPoint) {
            pastPoint = true;
        }
        return newPoint;
    }

    @Settable
    @Override
    public void setPoint (PointSimple myPoint) {
        super.setPoint(myPoint);
    }

    @Override
    public Mover clone () {
        return new MoverDirection(getPoint(), inherentSpeed, distanceLimit);
    }
}
