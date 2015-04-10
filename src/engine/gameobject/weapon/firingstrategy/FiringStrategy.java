package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.ObjectCollection;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    /**
     * Places projectiles into the world according to strategy
     * @param target
     * @param location
     * @param prototype
     * @return
     */
    public void execute(ObjectCollection world, Buffable target, PointSimple location, Buffer prototype);
        
}
