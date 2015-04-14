package engine.gameobject;

import javafx.geometry.Point2D;
import javafx.scene.effect.Effect;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Weapon;
import engine.gameobject.weapon.WeaponSimple;
import engine.pathfinding.EndOfPathException;
import engine.shop.tag.Tag;
import engine.shop.tag.TagSimple;


/**
 * Simple (and possibly only) implementation of the gameobject.
 * 
 * @author Jeremy, Kaighn
 *
 */
@Settable
public abstract class GameObjectSimple implements GameObject {
    protected String myImagePath;
    protected String myName;
    protected String myLabel;
    protected PointSimple myPoint;
    protected Health myHealth;
    protected Mover myMover;
    protected Graphic myGraphic;
    private Tag myTag;
    private Weapon myWeapon;

    public GameObjectSimple () {
        myImagePath = "";
        myLabel = "";
        myName = "";
        myPoint = new PointSimple();
        myHealth = new HealthSimple();
        myMover = new MoverPath();
        myGraphic = new Graphic();
        myTag = new TagSimple();
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

    // public void initializeNode () {
    // Image image = new Image(myImagePath);
    // ImageView imageView = new ImageView();
    // imageView.setImage(image);
    // myNode = imageView;
    // }

    @Override
    public void move () throws EndOfPathException {
        // TODO Auto-generated method stub
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(new Point2D(point.getX(), point.getY()));
        myGraphic.setPoint(myPoint);
    }

    @Settable
    @Override
    public void setSpeed (double speed) {
        myMover.setSpeed(speed);

    }

    @Override
    public Graphic getGraphic () {
        return myGraphic;
    }

    public BasicMover getMover () {
        return (BasicMover) myMover;
    }

    public String getImagePath () {
        return myImagePath;
    }

    //@Settable
    //TODO: Make settable
    public void setMover (Mover mover) {
        myMover = mover;
    }

    @Settable
    public void setImagePath (String imgpath) {
        myImagePath = imgpath;
    }

    @Settable
    public void setLabel (String label) {
        myLabel = label;
    }

    @Settable
    public void setPoint (PointSimple point) {
        myPoint = point;
    }

    @Settable
    public void setHealth (Health health) {
        myHealth = health;
    }

    @Settable
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Settable
    public void setName (String name) {
        myName = name;
    }

    @Override
    public double getValue () {
        return myWeapon.getValue();
    }
    
    @Override
    public Tag getTag() {
        return myTag;
    }
    
    @Settable
    public void setTag(Tag tag) {
        this.myTag = tag;
    }

}
