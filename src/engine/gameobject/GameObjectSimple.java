// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.gameobject.behaviors.BehaviorComponent;
import engine.gameobject.behaviors.BehaviorComponentConcrete;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthComponent;
import engine.gameobject.healths.HealthComponentConcrete;
import engine.gameobject.movers.Mover;
import engine.gameobject.movers.MoverNull;
import engine.gameobject.types.TypeComponent;
import engine.gameobject.types.TypeComponentConcrete;
import engine.gameobject.units.BuffComponent;
import engine.gameobject.units.BuffComponentConcrete;
import engine.gameobject.units.CollisionComponent;
import engine.gameobject.units.CollisionComponentConcrete;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;
import engine.shop.ShopComponent;
import engine.shop.ShopComponentConcrete;
import gae.editorView.GameObjectInformation;
import gameworld.ObjectCollection;


/**
 * Simple (and possibly only) implementation of the gameobject.
 *
 * @author Jeremy
 * @author Kaighn
 *
 */
@Settable
public class GameObjectSimple implements GameObject {
    private static final String TO_STRING_PREFACE = "GameObject: ";
    private TypeComponent myTypeComponent;
    private PointSimple myPoint;
    private Mover myMover;
    private Weapon myWeapon;
    private Graphic myGraphic;
    private BuffComponent myBuffComponent;
    private ShopComponent myShopComponent;
    private BehaviorComponent myBehaviorComponent;
    private HealthComponent myHealthComponent;
    private CollisionComponent myCollisionComponent;

    public GameObjectSimple () {
        myTypeComponent = new TypeComponentConcrete();
        myHealthComponent = new HealthComponentConcrete();
        myPoint = new PointSimple();
        myMover = new MoverNull();
        myWeapon = new NullWeapon();
        myGraphic = new Graphic();
        myBuffComponent = new BuffComponentConcrete(this);
        myShopComponent = new ShopComponentConcrete(myWeapon);
        myBehaviorComponent = new BehaviorComponentConcrete();
        myCollisionComponent = new CollisionComponentConcrete();
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

    @Settable
    @Override
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

    @Override
    public GameObject clone () {
        GameObjectSimple clone = new GameObjectSimple();
        clone.setPoint(new PointSimple(myPoint));
        clone.setWeapon(myWeapon.clone());
        clone.setMover(myMover.clone());
        clone.setGraphic(myGraphic.clone());
        return clone;
    }

    @Override
    public void move () throws EndOfPathException {
        PointSimple point = myMover.move(myPoint);
        myGraphic.rotate(point);
        setPoint(new PointSimple(point.getX(), point.getY()));
    }

    @Override
    public void setSpeed (double speed) {
        myMover.setSpeed(speed);
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

    @Override
    @Settable
    public void setMover (Mover mover) {
        myMover = mover;
    }

    @Override
    public void update (ObjectCollection world) {
        myBuffComponent.update();
        myWeapon.advanceTime();
        try {
            move();
        }
        catch (EndOfPathException e) {
        }
    }

    @Override
    public Graphic getGraphic () {
        return myGraphic;
    }

    /*
     * Component methods to follow
     */
    @Override
    public TypeComponent getTypeComponent () {
        return myTypeComponent;
    }

    @Override
    public ShopComponent getShopComponent () {
        return myShopComponent;
    }

    @Override
    public BehaviorComponent getBehaviorComponent () {
        return myBehaviorComponent;
    }

    @Override
    public HealthComponent getHealthComponent () {
        return myHealthComponent;
    }

    @Override
    public String toString () {
        return TO_STRING_PREFACE + GameObjectInformation.getInstance().getTitle(this);
    }

    @Override
    public CollisionComponent getCollisionComponent () {
        return myCollisionComponent;
    }

    @Override
    public BuffComponent getBuffComponent () {
        return myBuffComponent;
    }

}
