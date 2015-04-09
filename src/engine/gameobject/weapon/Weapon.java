package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradable.behavior.Behavior;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.Projectile;
import engine.gameobject.weapon.upgradable.FiringRate;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import gameworld.GameWorld;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 * 
 * @author Nathan Prabhu and Danny Oh
 *
 */
public abstract class Weapon {
    protected int timeSinceFire;
    protected double myRange;
    protected FiringRate myFiringRate;
    protected Projectile myProjectile;
    protected FiringStrategy myFiringStrategy;
    Map<Class<? extends Upgradable>, Upgradable> upgradables;
    protected UpgradeTree tree;

    /**
     * Attacks targets, inflicting the appropriate damage upon them.
     * 
     * @param location takes in the GameObject's location
     */
    public void fire (GameWorld world, PointSimple location) {
        if (canFire()) {
            fireAtEnemyInRange(world, location);
        }
        else {
            // TODO: Check that this is syncing with time correctly
            timeSinceFire++;
        }
    }

    /**
     * Adds a behavior to the given weapon. Will automatically upgrade existing one.
     * 
     * @param newBuff the Buff you want add to the projectile
     */
    public void addBuff (Buff newBuff) {
        myProjectile.addCollsionBehavior(newBuff);
    }

    /**
     * The value at which this weapon can be sold to the shop
     * 
     * @return
     */
    public double getValue () {
        return tree.getValue();
    };

    public void setProjectile (Projectile projectile) {
        myProjectile = projectile;
    }

    private void fireAtEnemyInRange (GameWorld world, PointSimple center) {
        ArrayList<GameObject> candidates =
                (ArrayList<GameObject>) world.objectsInRange(myRange, center);
        // TODO: In bloons, we choose from the candidates using first, last, strong, weak. We could
        // do something here as well using polymorphism. For now, we just choose a random one.
        if (!candidates.isEmpty()) {
            myFiringStrategy.execute(world, center, myProjectile);
            timeSinceFire = 0;
        }
    }

    // TODO: Get the math correct here
    private double firingRateToSeconds () {
        return 60.0 / myFiringRate.getRate();
    }

    private boolean canFire () {
        if (timeSinceFire > firingRateToSeconds())
            return true;
        return false;
    }

    public abstract double getRange ();

    public abstract double getFiringRate ();

}
