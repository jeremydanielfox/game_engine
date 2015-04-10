package engine.gameobject;

import javafx.geometry.Point2D;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Weapon;
import engine.gameobject.weapon.WeaponSimple;
import engine.pathfinding.EndOfPathException;
import engine.shop.Tag;


/**
 * Simple (and possibly only) implementation of the gameobject.
 * 
 * @author Jeremy, Kaighn
 *
 */
@Settable
public class GameObjectSimple implements GameObject {
    protected String myImagePath;
    protected String myLabel;
    protected PointSimple myPoint;
    protected Health myHealth;
    protected Mover myMover;
    protected Weapon myWeapon;
    protected Graphic myGraphic;
    private Tag myTag;

    public GameObjectSimple () {
        myImagePath = "";
        myLabel = "";
        myPoint = new PointSimple();
        myHealth = new HealthSimple();
        myMover = new MoverPath();
        myWeapon = new WeaponSimple();
        myGraphic = new Graphic();
        //myTag = new NameTag();
    }

    public void update(){
        if(isDead()){
            onDeath();
            return;
        }
        try {
            move();
        }
        catch (EndOfPathException e){
            //TODO: Catch and perform end of path duties
        }
        //myWeapon.fire(world, myPoint);
    }
    
    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    public void onDeath () {

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

//    public void initializeNode () {
//        Image image = new Image(myImagePath);
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        myNode = imageView;
//    }

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
        // TODO Auto-generated method stub

    }

    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return myGraphic;
    }
    
    public BasicMover getMover () {
        return (BasicMover) myMover;
    }

    @Settable
    public void setMover (Mover mover) {
        myMover = mover;
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
    public void setPoint (PointSimple point) {
        myPoint = point;
    }

    @Settable
    void setHealth (Health health) {
        myHealth = health;
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
