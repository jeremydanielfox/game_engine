package engine.gameobject.units;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import gameworld.ObjectCollection;

public interface Firing {

    /**
     * Fires, perhaps using a weapon, in the world at target
     * @param world
     * @param target
     */
    public void fire (ObjectCollection world, GameObject target);
    
    /**
     * Sets weapon to parameter weapon
     * @param weapon
     */
    public void setWeapon(Weapon weapon);
    
    /**
     * Returns firing object's weapon
     */
    public Weapon getWeapon();
}
