package engine.gameobject.movers;

import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.pathfinding.EndOfPathException;


@Settable
public class MoverUser extends BasicMover {
    private DirectionHandler myHandler;
    
    public MoverUser(double speed) {
        super();
        this.inherentSpeed = speed;
        myHandler = new DirectionHandler();
    }

    public MoverUser () {
        super();
        this.inherentSpeed = 1;
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
