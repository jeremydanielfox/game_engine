package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.GameWorld;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    public void execute(GameWorld world, ArrayList<GameObject> candidate, PointSimple location, Projectile prototype);
        
}
