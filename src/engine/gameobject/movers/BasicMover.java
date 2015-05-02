package engine.gameobject.movers;

import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;


@Settable
public abstract class BasicMover implements Mover {
    private static final int PERCENTAGE = 100;
    protected double inherentSpeed;
    protected double speedModifier;
    protected double myDistance;
    protected boolean frozen;

    public BasicMover () {
        this(0);
    }

    public BasicMover (double speed) {
        myDistance = 0;
        inherentSpeed = speed;
        frozen = false;
        speedModifier = 1;
    }

    @Override
    public abstract PointSimple move (PointSimple current) throws EndOfPathException;

    @Settable
    @Override
    public void setSpeed (double speed) {
        inherentSpeed = speed;
    }

    @Override
    public double getSpeed () {
        return inherentSpeed;
    }

    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }

    @Override
    public void speedBuff (double percentage) {
        speedModifier = speedModifier + percentage / PERCENTAGE;
    }

    @Override
    public abstract Mover clone ();

    protected double currentSpeed () {
        if (!frozen) {
            return inherentSpeed * speedModifier;
        }
        return 0;
    }
}
