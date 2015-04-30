package engine.gameobject;

import engine.pathfinding.EndOfPathException;


public class MoverNull extends BasicMover implements Mover {
    
    public MoverNull(){
        super(0);
    }
    
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        return current;
    }

    @Override
    public void setSpeed (double speed) {
    }

    @Override
    public Mover clone () {
        return new MoverNull();
        // TODO Auto-generated method stub
    }

    @Override
    public double getSpeed () {
        return 0;
    }

    @Override
    public void speedBuff (double percentage) {
        //Do nothing
    }

}
