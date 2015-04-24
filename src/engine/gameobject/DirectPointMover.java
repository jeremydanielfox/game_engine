package engine.gameobject;

import engine.pathfinding.EndOfPathException;


public class DirectPointMover extends MoverPoint {

    public DirectPointMover () {
        super();
    }

    public DirectPointMover (PointSimple point, double speed) {
        super(point, speed);
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        PointSimple newPoint = PointSimple.pointOnLine(current, getPoint(), currentSpeed());
        if (PointSimple.pointInBetween(current, newPoint, getPoint())) {
            throw new EndOfPathException();
        }
        return newPoint;
    }

    @Override
    public Mover clone () {
        return new DirectPointMover(getPoint(), inherentSpeed);
    }

}
