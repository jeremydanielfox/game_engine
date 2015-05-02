package engine.gameobject.movers;

import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;


public class MoverNull extends BasicMover implements Mover {
    private static final int NO_SPEED = 0;
    public MoverNull(){
        super(NO_SPEED);
    }
    
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        return current;
    }

    @Override
    public void setSpeed (double speed) {
        // A null mover has no speed, so setting it doesn't change anything
    }

    @Override
    public Mover clone () {
        return new MoverNull();
    }

    @Override
    public double getSpeed () {
        return NO_SPEED;
    }

    @Override
    public void speedBuff (double percentage) {
        //Do nothing
    }

}
