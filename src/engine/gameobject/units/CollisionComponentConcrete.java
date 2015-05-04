package engine.gameobject.units;

import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;


/**
 * Concrete implementation of the collision component
 * 
 * @author Jeremy
 *
 */
public class CollisionComponentConcrete implements CollisionComponent {
    private Collider myCollider;
    private PointSimple myPoint;

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
    }
}
