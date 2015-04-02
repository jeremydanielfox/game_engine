package engine.gameobject;

import javafx.geometry.Point2D;

/**
 * 
 * @author Jeremy
 *
 */
public class ConcreteGameObject implements GameObject, Cloneable {
    private String myLabel;
    private Point2D myPoint;
    private Health myHealth;
    private Mover myMover;
    private Weapon myWeapon;

    @Override
    public void move (double x, double y) {
        Pointlike point = myMover.move(x, y);
        myPoint = new Point2D(point.getX(), point.getY());
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
    public Pointlike getPoint () {
        return new PointSimple(myPoint);
    }

    @Override
    public Weapon getWeapons () {
        return myWeapon;
    }

}
