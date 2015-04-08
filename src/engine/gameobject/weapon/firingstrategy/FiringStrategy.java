package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.weapon.upgradetree.Weapon;

/**
 * Encapsulates how a weapon is fired. Examples would be a single projectile or an
 * area of attack.
 * @author Nathan Prabhu
 *
 */
public interface FiringStrategy {
    
    public void fire(Weapon weapon);

}
