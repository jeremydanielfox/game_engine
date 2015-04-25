package engine.gameobject.units;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;


public interface Colliding {

    /**
     * Returns object's collider
     */

    public Collider getCollider ();

    /**
     * Sets collider to parameter collider.
     * 
     * @param collider
     */
    public void setCollider (Collider collider);

    /**
     * Collide with target. Will impart buffs to target if target is relevant.
     * 
     * @param target
     */
    public void collide (GameObject target);

    /**
     * Explodes in the given world at given location
     * 
     * @param world
     */
    public void explode (ObjectCollection world);

}
