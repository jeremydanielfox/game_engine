package engine.gameobject.weapon;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import gameworld.ObjectCollection;
import java.util.List;
import javafx.beans.property.DoubleProperty;


public interface Weapon {

    /**
     * Attacks targets, inflicting the appropriate damage upon them.
     *
     * @param location takes in the GameObject's location
     */
    public void fire (ObjectCollection world, GameObject target, PointSimple location);

    /**
     * Sets the weapon's range to parameter range
     * 
     * @param range
     */
    public void setRange (double range);

    /**
     * Sets the weapon's firing rate to firingRate per second
     * 
     * @param firingRate
     */
    public void setFiringRate (double firingRate);

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
     * 
     * @param projectile
     */
    public abstract void setProjectile (GameObject projectile);

    /**
     * Advances time stored in weapon by 1
     */
    public void advanceTime ();

    /**
     * Returns range of weapon
     * 
     * @return
     */
    public abstract double getRange ();
    
    public abstract DoubleProperty getRangeProperty();

    /**
     * returns firing rate of weapon
     * 
     * @return
     */
    public abstract double getFiringRate ();

    public abstract List<UpgradeBundle> getNextUpgrades ();

    public void applyUpgrades (UpgradeBundle bundle);

    public void setFiringStrategy (FiringStrategy newStrategy);

    public Weapon clone ();

}
