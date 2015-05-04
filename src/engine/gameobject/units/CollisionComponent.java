package engine.gameobject.units;

import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import gameworld.ObjectCollection;


/**
 * Interface for the collision component
 * 
 * @author Jeremy
 *
 */
public interface CollisionComponent {

    public Collider getCollider ();

    @Settable
    public void setCollider (Collider collider);

    public void explode (ObjectCollection world);

    public void collide (GameObject target);
}
