package gameobject;

import java.util.List;
import javafx.geometry.Point2D;


public class GameObjectSimple implements GameObject {
    private String myLabel;
    private Point2D myPoint;
    private Health myHealth;
    private Mover myMover;
    private Actor myActor;
    private List<Weapon> myWeapons;

    @Override
    public void act (GameObject a, GameObject b) {
        myActor.act(a, b);
    }

    @Override
    public void move (double x, double y) {
        Pointlike point = myMover.move(x, y);
        myPoint = new Point2D(point.getX(), point.getY());
    }

    @Override
    public void addWeapon (Weapon weapon) {
        myWeapons.add(weapon);
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
    public List<Weapon> getWeapons () {
        return myWeapons;
    }

}
