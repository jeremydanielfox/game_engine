package gameobject;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Jeremy
 *
 */
public class GameObjectSimple implements GameObject {
    private Node myNode;
    private String myImagePath;
    private String myLabel;
    private Point2D myPoint;
    private Health myHealth;
    private Mover myMover;
    private List<Weapon> myWeapons;

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

    public void initializeNode () {
        Image image = new Image(myImagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        myNode = imageView;
    }

}
