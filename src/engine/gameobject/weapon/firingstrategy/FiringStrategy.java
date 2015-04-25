package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;


/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * 
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {

    /**
     * Places projectiles into the world according to strategy
     * 
     * @param target
     * @param location
     * @param prototype
     * @return
     */
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype);

}
