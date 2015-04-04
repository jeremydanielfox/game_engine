package engine.gameobject;

import javafx.scene.Node;
import engine.gameobject.weapon.Weapon;
import engine.grid.GridCell;
import engine.pathfinding.EndOfPathException;


/**
 * 
 * @author Jeremy
 *
 */
public class ConcreteGameObject implements GameObject, Cloneable {
    private String myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Weapon myWeapon;
    private GridCell myDimensions;

    @Override
    public void move () throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(point.getX(), point.getY());
    }


    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    // temporary
    public GameObject clone () {
        try {
            return (GameObject) super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(this.getLabel() + " can't be cloned");
            return null;
        }
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }


    @Override
    public void setSpeed (double speed) {
        myMover.setSpeed(speed);
    }

    @Override
    public GridCell getGridDimensions () {
        return myDimensions;
    }

    @Override
    public void addWeapon (gameobject.Weapon weapon) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public gameobject.Weapon getWeapon () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getGraphic () {
        // TODO Auto-generated method stub
        return null;
    }

}
