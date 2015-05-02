package engine.gameobject.movers;

import View.ViewConcrete2;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;


@Deprecated
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
        if (PointSimple.pointInBetween(current, newPoint, getPoint()) ||
            PointSimple.pointInBetween(new PointSimple(0, 0),
                                       new PointSimple(ViewConcrete2.getWorldWidth(), ViewConcrete2
                                               .getWorldHeight()), newPoint)) {
            throw new EndOfPathException();
        }

        return newPoint;
    }

    @Override
    public Mover clone () {
        return new DirectPointMover(getPoint(), inherentSpeed);
    }

}
