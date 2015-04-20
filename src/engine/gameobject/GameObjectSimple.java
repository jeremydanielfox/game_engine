package engine.gameobject;

import javafx.geometry.Point2D;
import engine.fieldsetting.Settable;
import engine.gameobject.labels.Label;
import engine.gameobject.labels.LabelConcrete;
import engine.gameobject.labels.SimpleLabel;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffTracker;
import engine.gameobject.units.Collider;
import engine.gameobject.units.Colliding;
import engine.gameobject.units.Firing;
import engine.gameobject.weapon.NullWeapon;
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
public class GameObjectSimple implements GameObject{
    private Label myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Graphic myGraphic;
    private GameObjectTag myTag;
    private BuffTracker myBuffs;
    private Weapon myWeapon;
    private Collider myCollider;
    
    public GameObjectSimple () {
        myLabel = new SimpleLabel();
        myPoint = new PointSimple();
        myHealth = new HealthSimple();
        myMover = new MoverPath();
        myGraphic = new Graphic();
        myTag = new GameObjectTagSimple();
        myBuffs = new BuffTracker();
        myWeapon = new NullWeapon();
        myCollider = new Collider();
    }

/*
 * Buffable methods follow
 */
    
    /**
     * Give the object a buff e.g. burn this object
     */
    public void addBuff(Buff buff){
        myBuffs.addBuff(buff, this);
    }
    
/*
 * Firing methods follow
 */
    
    @Settable
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
    }
    
    public void fire(ObjectCollection world, GameObject target){
        myWeapon.fire(world, target, myPoint);
    }
    
    public Weapon getWeapon(){
        return myWeapon;
    }
    
/*
 * Colliding methods follow
 */
    
    public Collider getCollider(){
        return myCollider;
    }
    
    public void setCollider(Collider collider){
        myCollider = collider;
    }

    public void explode(ObjectCollection world){
        myCollider.explode(world, myPoint);
    }
    
    public void collide(GameObject target){
        myCollider.collide(target);
        changeHealth(-1);
    }
    
/*
 * Prototype methods follow
 */
    @Override
    public double getRange(){
        return myWeapon.getRange();
    }
    
    //TODO: Tag cloning not done, Weapon upgrade cloning not done
    @Override
    public GameObject clone () {
        GameObject clone = new GameObjectSimple();
        clone.setLabel(myLabel);
        clone.setTag(myTag);
        clone.setPoint(new PointSimple(myPoint));
        clone.setHealth(myHealth.clone());
        clone.setGraphic(myGraphic.clone());
        clone.setWeapon(myWeapon.clone());
        clone.setCollider(myCollider.clone());
        clone.setMover(myMover.clone());
        return clone;
    }
    
    @Override
    public GameObjectTag getTag () {
        return myTag;
    }
 
/*
 * Purchasable methods follow
 */
    
    @Override
    public Graphic getGraphic () {
        return myGraphic;
    }
    
    @Override
    public double getValue () {
        return myWeapon.getValue();
    }
    
/*
 * Movable, Health methods follow
 */
    @Override
    public void move () throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        setPoint(new PointSimple(new Point2D(point.getX(), point.getY())));
    }
    
    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }
    
    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }
    
    @Override
    public void setSpeed (double speed) {
        myMover.setSpeed(speed);
    }
    
/*
 * GameObject specific methods follow
 */

    @Override
    public Label getLabel () {
        return myLabel;
    }

    @Settable
    public void setLabel (Label label) {
        myLabel = label;
    }
    
    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }

    @Settable
    public void setPoint (PointSimple point) {
        myPoint = point;
        myGraphic.setPoint(point);
    }

    @Override
    public Mover getMover () {
        return myMover;
    }

    /**
     * Note that this takes in a new health object.
     * Should only be used when setting to a new health, not for damage.
     * @param health
     */
    @Settable
    public void setHealth (Health health) {
        myHealth = health;
    }

    // @Settable
    public void setMover (Mover mover) {
        myMover = mover;
    }

    @Settable @Override
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Settable
    public void setTag (GameObjectTag tag) {
        this.myTag = tag;
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
    public void update (ObjectCollection world) {
        myBuffs.update(this);
        myWeapon.advanceTime();
        try {
            move();
        }
        catch (EndOfPathException e) {
            //TODO: Encode end of path behaviors. For now, just die.
            changeHealth(-10000);
        }
        if(isDead()){
            onDeath(world);
        }
    }

    @Override
    public void onDeath (ObjectCollection world) {
        explode(world);
        //TODO: Birthing other units? Like blue bloon -> red bloon?
    }

}
