package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


@Settable
public class MoverUser extends BasicMover {
    private DirectionHandler myHandler;
    
    public MoverUser(double speed) {
        this.inherentSpeed = speed;
        myHandler = new DirectionHandler();
    }

    public MoverUser () {
        this.inherentSpeed = 5;
        myHandler = new DirectionHandler();
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        PointSimple direction = myHandler.getDirection();
        return current.add(direction.multiply(currentSpeed()));
    }

    @Settable
    public void setGraphic (Graphic graphic) {
        myHandler.setGraphic(graphic);
    }

    @Override
    public Mover clone () {
        MoverUser mover =  new MoverUser();
        mover.myHandler = myHandler.clone();
        return mover;
    }
}
