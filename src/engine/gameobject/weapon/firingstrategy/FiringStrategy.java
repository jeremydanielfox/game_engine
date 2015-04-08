package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import gameworld.GameWorld;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    public void execute(GameWorld world, PointSimple location, Projectile prototype);

}
