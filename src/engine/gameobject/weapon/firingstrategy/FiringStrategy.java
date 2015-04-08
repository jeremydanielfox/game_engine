package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.Weapon;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    public void execute(GameObject o);

}
