package engine.gameobject.weapon;

import java.util.List;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.gameobject.weapon.firingstrategy.Projectile;
import gameworld.ObjectCollection;


public interface Weapon {

    /**
     * Attacks targets, inflicting the appropriate damage upon them.
     * 
     * @param location takes in the GameObject's location
     */
    public void fire (ObjectCollection world, PointSimple location);

    /**
     * Adds a behavior to the given weapon. Will automatically upgrade existing one.
     * 
     * @param newBuff the Buff you want add to the projectile
     */
    public abstract void addBuff (Buff newBuff);

    /**
     * The value at which this weapon can be sold to the shop
     * 
     * @return
     */
    public abstract double getValue ();

    /**
     * Sets the projectile the weapon shoots
     * @param projectile
     */
    public abstract void setProjectile (Projectile projectile);

    /**
     * Returns range of weapon
     * @return
     */
    public abstract double getRange ();

    /**
     * returns firing rate of weapon
     * @return
     */
    public abstract double getFiringRate ();

}
