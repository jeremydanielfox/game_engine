package engine.gameobject.weapon.upgradable.behavior;

import engine.gameobject.GameObject;

/**
 * Allows enemy to be frozen for a specific amount of time
 * @author Nathan Prabhu
 *
 */
public interface Freeze extends Behavior {
    
    public void apply(GameObject target);
       
}
