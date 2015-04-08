package engine.gameobject.weapon.upgradable.behavior.damage;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradable.behavior.Behavior;

/**
 * Applies a specified amount of damage to an enemy's health
 * @author Nathan Prabhu
 *
 */
public class DamageSimple implements Damage, Behavior {
    
    private double damage;

    public DamageSimple (double damage) {
        this.damage = damage; 
    }

    @Override
    public void apply (GameObject target) {
       target.changeHealth(-getDamage());
    }

    @Override
    public double getDamage () {
        return damage;
    }
}
