package engine.gameobject;

import engine.pathfinding.EndOfPathException;

public class BasicMover implements Mover {
    double inherentSpeed;
    double slowPercent;
    boolean frozen;
    
    /**
     * This switch statement is not worth having polymorphism/using a state pattern.
     * No incompatible extensions will be made.
     */
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        if (frozen == true)
            return current;
        else
            //TODO:
            //return inherentSpeed*(1-slowPercent)+ current;
        return null;
    }

    @Override
    public void setSpeed (double speed) {
        inherentSpeed = speed;
    }
    
    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }
    
    /**
     * 
     * @param percentage i.e. .90 for 90%
     */
    public void slow (double percentage){
        slowPercent = percentage;
    }
    
    public void unSlow (){
        slowPercent = 0;
    }
    

}
