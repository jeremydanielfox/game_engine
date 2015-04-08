package engine.gameobject;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.upgradetree.Weapon;
import engine.gameobject.weapon.upgradetree.WeaponSimple;
import engine.pathfinding.EndOfPathException;


/**
 * This is a test class for GameObjects, to be used only by the Game Engine for testing purposes.
 * The
 * GameObjectSimpleTest has a Node that is a Circle.
 * 
 * @author Jeremy
 *
 */
public class GameObjectSimpleTest implements GameObject {
    private Node myNode;
    private String myImagePath;
    private String myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Weapon myWeapon;
    private Graphic myGraphic;

    public GameObjectSimpleTest () {
        //createNode();
        myImagePath = "robertDuvall.jpg";
        myLabel = "test object";
        myPoint = new PointSimple(300,300);
        myHealth = new HealthSimple();
        myMover = new MoverPath(null, 0);
        //myWeapon = new WeaponSimple(0, 0, null, null);
        myGraphic = new Graphic(100, 100, myImagePath);
        myGraphic.setPoint(myPoint);
    }

    //This method is outdated. Now encapsulated in graphics class.
//    private void createNode () {
//        Circle circle = new Circle();
//        circle.setFill(Color.ALICEBLUE);
//        myNode = circle;
//    }

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

    public void initializeNode () {
        Image image = new Image(myImagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        myNode = imageView;
    }

    @Override
    public void move () throws EndOfPathException {
        // TODO Auto-generated method stub
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(new Point2D(point.getX(), point.getY()));
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return myGraphic;
    }

    @Settable
    void setImagePath (String imgpath) {
        myImagePath = imgpath;
    }

    @Settable
    void setLabel (String label) {
        myLabel = label;
    }

    @Settable
    void setPoint (PointSimple point) {
        myPoint = point;
        myGraphic.setPoint(point); 
    }

    @Settable
    void setHealth (Health health) {
        myHealth = health;
    }

    @Settable
    void setMover (Mover mover) {
        myMover = mover;
    }

    @Settable
    void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Override
    public Weapon getWeapon () {
        return myWeapon;
    }

    @Settable
    @Override
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
    }
}
