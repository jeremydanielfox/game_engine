package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;

//TODO: These instance variables should be private(?)
@Settable
public abstract class BasicMover implements Mover {
    double inherentSpeed;
    double speedModifier;
    double myDistance;
    boolean frozen;

    public BasicMover () {
        inherentSpeed = 0;
        speedModifier = 0;
        myDistance = 0;
        frozen = false;
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
    
    public double getSpeed(){
        return inherentSpeed;
    }
    
    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }

    public void speedBuff (double percentage) {
        speedModifier = speedModifier + percentage/100;
    }

    @Override
    public abstract Mover clone ();

    protected double currentSpeed () {
        if (frozen != true) {
            return inherentSpeed * speedModifier;
        }
        return 0;
    }
}
