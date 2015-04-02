package engine.gameobject;

import engine.grid.GridCell;
import engine.pathfinding.EndOfPathException;
import javafx.geometry.Point2D;

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
    public void move() throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(point.getX(), point.getY());
    }

    @Override
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
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
    public Weapon getWeapon() {
        return myWeapon;
    }

	@Override
	public void setSpeed(double speed) {
		myMover.setSpeed(speed);
	}

	@Override
	public GridCell getGridDimensions() {
		return myDimensions;
	}

}
