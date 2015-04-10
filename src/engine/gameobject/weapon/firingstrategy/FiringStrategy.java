package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.GameWorld;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    /**
     * Returns the appropriate projectiles according to the firingstrategy and inputs
     * @param targets
     * @param location
     * @param prototype
     * @return
     */
    public List<Buffer> execute(List<Buffable> targets, PointSimple location, Buffer prototype);
        
}
