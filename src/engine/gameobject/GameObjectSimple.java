package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.gameobject.behaviors.Behavior;
import engine.gameobject.behaviors.BehaviorTracker;
import engine.gameobject.labels.SimpleType;
import engine.gameobject.labels.Type;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffTracker;
import engine.gameobject.units.UpgradeType;
import engine.gameobject.units.Collider;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;
import engine.shop.RangeDisplay;
import engine.shop.ShopTag;
import engine.shop.ShopTagSimple;
import gameworld.ObjectCollection;


/**
 * Simple (and possibly only) implementation of the gameobject.
 *
 * @author Jeremy, Kaighn
 *
 */
@Settable
public class GameObjectSimple implements GameObject {
    private Type myType;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private BuffTracker myBuffs;
    private Weapon myWeapon;
    private Collider myCollider;
    private BehaviorTracker myBehaviors;
    private Graphic myGraphic;
    private ShopTag myShopTag;

    public GameObjectSimple () {
        myType = new SimpleType();
        myPoint = new PointSimple();
        myHealth = new HealthSimple();
        myMover = new MoverNull();
        myBuffs = new BuffTracker();
        myWeapon = new NullWeapon();
        myCollider = new Collider();
        myBehaviors = new BehaviorTracker();
        myGraphic = new Graphic();
        myShopTag = new ShopTagSimple();
    }

    /*
     * Buffable methods follow
     */

    /**
     * Give the object a buff e.g. burn this object
     */
    @Override
    public void receiveBuff (Buff buff) {
        myBuffs.receiveBuff(buff, this);
    }

    public void addImmunity (Class<? extends Buff> immunity, UpgradeType buffType) {
        myBuffs.addImmunity(immunity, buffType);
    }

    /*
     * Firing methods follow
     */

    @Override
    @Settable
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
    }

    @Override
    public void fire (ObjectCollection world, GameObject target) {
        myWeapon.fire(world, target, myPoint);
        myGraphic.rotate(target.getPoint());
    }

    @Override
    public Weapon getWeapon () {
        return myWeapon;
    }

    /*
     * Colliding methods follow
     */

    @Override
    public Collider getCollider () {
        return myCollider;
    }

    @Override
    @Settable
    public void setCollider (Collider collider) {
        myCollider = collider;
    }

    @Override
    public void explode (ObjectCollection world) {
        myCollider.explode(world, myPoint);
    }

    @Override
    public void collide (GameObject target) {
        myCollider.collide(target);
        changeHealth(-1);
    }

    /*
     * Purchasable methods follow
     */

    @Override
    public String getName () {
        return myShopTag.getName();
    }

    @Override
    public String getDescription () {
        return myShopTag.getDescription();
    }

    @Override
    public Graphic getShopGraphic () {
        return myShopTag.getShopGraphic();
    }

    @Settable
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Override
    public double getValue () {
        return myWeapon.getValue();
    }

    // TODO: Weapon upgrade cloning not done
    @Override
    public GameObject clone () {
        GameObjectSimple clone = new GameObjectSimple();
        clone.setLabel(myType);
        clone.setPoint(new PointSimple(myPoint));
        clone.setHealth(myHealth.clone());
        clone.setWeapon(myWeapon.clone());
        clone.setCollider(myCollider.clone());
        clone.setMover(myMover.clone());
        clone.setGraphic(myGraphic.clone());
        clone.setShopTag(myShopTag.clone());
        clone.myBehaviors = myBehaviors;//TODO: ??????????
        return clone;
    }

    /*
     * Movable, Health methods follow
     */
    @Override
    public void move () throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        myGraphic.rotate(point);
        setPoint(new PointSimple(point.getX(), point.getY()));
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
     * EndBehaviorful methods follow
     */

    public void addOnDeathBehavior (Behavior behavior) {
        myBehaviors.addOnDeath(behavior);
    }

    public void clearDeathBehavior () {
        myBehaviors.clearDeath();
    }

    public void addEndOfPathBehavior (Behavior behavior) {
        myBehaviors.addEndOfPath(behavior);
    }

    public void clearEndOfPathBehavior () {
        myBehaviors.clearEndOfPath();
    }

    /*
     * GameObject specific methods follow
     */

    @Override
    public Type getLabel () {
        return myType;
    }

    @Override
    @Settable
    public void setLabel (Type label) {
        myType = label;
    }

    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }

    @Override
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
     * 
     * @param health
     */
    @Override
    @Settable
    public void setHealth (Health health) {
        myHealth = health;
    }

    @Override
    @Settable
    public void setMover (Mover mover) {
        myMover = mover;
    }

    @Override
    public void update (ObjectCollection world) {
        myBuffs.update(this);
        myWeapon.advanceTime();
        try {
            move();
        }
        catch (EndOfPathException e) {
            myBehaviors.onEndOfPath(world, this);
            // Note that something doesn't always have to die at end of path, but if it doesn't die,
            // it may
            // keep doing endofpath over and over again
        }
    }

    @Override
    public void onDeath (ObjectCollection world) {
        myBehaviors.onDeath(world, this);
    }

    @Override
    public RangeDisplay getRangeDisplay () {
        return new RangeDisplay(getName(), myGraphic, myWeapon.getRangeProperty());
    }

    @Override
    public Graphic getGraphic () {
        return myGraphic;
    }

    @Settable
    public void setShopTag (ShopTag shopTag) {
        myShopTag = shopTag;
    }

    @Override
    public String toString () {
        return "GameObject: " + myType.getName();
    }

}
