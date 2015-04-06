package engine.gameobject.weapon;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradable.behavior.Behavior;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 * @author Nathan Prabhu
 *
 */
public interface Weapon {
    
    /**
     * Attacks targets, inflicting the appropriate damage upon them. 
     * @param targets
     */
    public void attack (GameObject... targets);
    
    /**
     * Adds a behavior to the given weapon. 
     * @param behavior
     */
    public void addBehavior(Behavior behavior);
  
    public double getRange ();

    public double getFiringRate ();
    
    /**
     * The value at which this weapon can be sold to the shop
     * @return
     */
    public double getValue (); 
    
}
