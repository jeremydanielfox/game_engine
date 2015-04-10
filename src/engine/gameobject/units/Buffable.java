package engine.gameobject.units;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.Weapon;

public interface Buffable extends GameObject{
    /**
     * Buff this unit with buff
     * @param buff
     */
    public void addBuff(Buff buff);
    
    /**
     * Sets the GameObject's Weapon
     */
    public void setWeapon (Weapon weapon);

    /**
     * Returns the GameObject's Weapon
     */
    public Weapon getWeapon();

}
