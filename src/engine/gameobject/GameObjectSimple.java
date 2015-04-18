package engine.gameobject;

import javafx.geometry.Point2D;
import engine.fieldsetting.Settable;
import engine.gameobject.labels.Label;
import engine.gameobject.labels.LabelConcrete;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;
import engine.shop.tag.GameObjectTag;
import engine.shop.tag.GameObjectTagSimple;
import gae.listView.DeepCopy;
import gameworld.ObjectCollection;


/**
 * Simple (and possibly only) implementation of the gameobject.
 * 
 * @author Jeremy, Kaighn
 *
 */
@Settable
public class GameObjectSimple implements GameObject {
    private Label myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Graphic myGraphic;
    private Weapon myWeapon;
    private GameObjectTag myTag;

    public GameObjectSimple () {
        myLabel = new LabelConcrete();
        myPoint = new PointSimple();
        myHealth = new HealthSimple();
        myMover = new MoverPath();
        myGraphic = new Graphic();
        myTag = new GameObjectTagSimple();
    }

    public GameObject clone () {
        return (GameObject) DeepCopy.copy(this);
    }

    // public void initializeNode () {
    // Image image = new Image(myImagePath);
    // ImageView imageView = new ImageView();
    // imageView.setImage(image);
    // myNode = imageView;
    // }

    @Override
    public void move () throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        setPoint(new PointSimple(new Point2D(point.getX(), point.getY())));
    }

    @Override
    public Label getLabel () {
        return myLabel;
    }

    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }

    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    @Override
    public Mover getMover () {
        return myMover;
    }

    @Override
    public Graphic getGraphic () {
        return myGraphic;
    }

    @Override
    public Weapon getWeapon () {
        return myWeapon;
    }

    @Override
    public GameObjectTag getTag () {
        return myTag;
    }

    @Settable
    public void setLabel (Label label) {
        myLabel = label;
    }

    @Settable
    public void setPoint (PointSimple point) {
        myPoint = point;
        myGraphic.setPoint(point);
    }

    @Settable
    public void setHealth (Health health) {
        myHealth = health;
    }

    // @Settable
    public void setMover (Mover mover) {
        myMover = mover;
    }

    @Settable
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Settable
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
    }

    @Settable
    public void setTag (GameObjectTag tag) {
        this.myTag = tag;
    }

    @Override
    public double getValue () {
        return myWeapon.getValue();
    }

    // // added because tag is broken and I can't test - Kei
    // public String getName () {
    // // works with return myName - editor not compatible
    // return myTag.getName();
    // }

    // public String getLabel () {
    // return myTag.getLabel();
    // }
    @Override
    public double getRange () {
        return myWeapon.getRange();
    }

    @Override
    public void setSpeed (double speed) {
        myMover.setSpeed(speed);
    }

    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    @Override
    public void update (ObjectCollection world) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDeath (ObjectCollection world) {
        // TODO Auto-generated method stub
        
    }

}
