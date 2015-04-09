package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;


@Settable
public class BasicMover implements Mover {
    Path myPath;
    double inherentSpeed;
    double speedModifier;
    double myDistance;
    boolean frozen;

    public BasicMover(Path pf, double speed){
            myDistance = 0;
            myPath = pf;
            inherentSpeed = speed;
            frozen = false;
            speedModifier = 1;
    }
    /**
     * This switch statement is not worth having polymorphism/using a state pattern.
     * No incompatible extensions will be made.
     */
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        if (frozen != true)
            myDistance += inherentSpeed * speedModifier;
        return myPath.getNextLocation(current, myDistance);
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        inherentSpeed = speed;
    }

    @Settable
    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * 
     * @param percentage i.e. .90 for 90% speedup
     */
    public void speedBuff (double percentage) {
        speedModifier = speedModifier + percentage;
    }

    
    public BasicMover clone(){
        return new BasicMover(myPath, inherentSpeed);
    }

    @Settable
    public void setPath (Path path) {
        myPath = path;
    }
}
