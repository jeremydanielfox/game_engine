package engine.gameobject.weapon.upgradable.behavior.damage;

import engine.gameobject.weapon.upgradable.behavior.Behavior;

/**
 * A property that specifies the amount of damage to an enemy's health
 * @author Nathan Prabhu
 *
 */
public interface Damage extends Behavior {
    
    public double getDamage ();
}
