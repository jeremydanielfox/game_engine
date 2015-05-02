package engine.gameobject.movers;

import View.ViewConcrete2;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;


@Settable
public class MoverDirection extends MoverPoint {
    private static final int SCREEN_OFFSET = 10;
    private static final int DEFAULT_DISTANCE_LIMIT = 10;
    private static final int DEFAULTL_SPEED = 1;
    private static final int DEFAULT_Y_POSITION = 0;
    private static final int DEFAULT_X_POSITION = 0;
    private double distanceLimit;
    private double distanceTraveled;
    private boolean pastPoint;

    public MoverDirection () {
        this(new PointSimple(DEFAULT_X_POSITION, DEFAULT_Y_POSITION), DEFAULTL_SPEED,
             DEFAULT_DISTANCE_LIMIT);
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
        if (!PointSimple.pointInBetween(new PointSimple(DEFAULT_X_POSITION, DEFAULT_Y_POSITION),
                                        new PointSimple(ViewConcrete2.getWorldWidth() -
                                                        SCREEN_OFFSET,
                                                        ViewConcrete2.getWorldHeight() -
                                                                SCREEN_OFFSET),
                                        newPoint)) {
            throw new EndOfPathException();
        }
        return newPoint;
    }

    @Settable
    public void setRange (double range) {
        distanceLimit = range;
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        super.setSpeed(speed);
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
