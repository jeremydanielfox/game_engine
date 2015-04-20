package engine.gameobject.weapon;

import java.util.List;
import java.util.stream.Collectors;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingrate.FiringRate;
import engine.gameobject.weapon.firingrate.FiringRateUpgrade;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.Projectile;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.range.Range;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import gameworld.ObjectCollection;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 * 
 * @author Nathan Prabhu and Danny Oh
 *
 */
public class BasicWeapon implements Weapon {
    private int timeSinceFire;
    private Range myRange;
    private FiringRate myFiringRate;
    private GameObject myProjectile;
    private FiringStrategy myFiringStrategy;
    private ClassSet<Upgrade> upgradables;
    private UpgradeTree tree;

    public BasicWeapon () {
        timeSinceFire = 0;
        myRange = new RangeUpgrade(250);
        myFiringRate = new FiringRateUpgrade(.5);
        myFiringStrategy = new SingleProjectile();
        upgradables = new ClassSet<Upgrade>(new Upgrade[] { myRange,
                                                           myFiringRate });
    }

    @Override
    public Weapon clone(){
        BasicWeapon clone = new BasicWeapon();
        clone.setFiringRate(myFiringRate.getRate());
        clone.setRange(myRange.getRange());
        clone.setFiringStrategy(myFiringStrategy);
        clone.setProjectile(myProjectile);
        return clone;
    }
    
    @Override @Settable
    public void setRange (double range) {
        myRange = new RangeUpgrade(range);
    }

    @Override @Settable
    public void setFiringRate (double firingRate){
        myFiringRate = new FiringRateUpgrade(firingRate);
    }
    
    @Override @Settable
    public void setFiringStrategy (FiringStrategy newStrategy) {
        myFiringStrategy = newStrategy;
    }

    @Override @Settable
    public void setProjectile (GameObject projectile) {
        myProjectile = projectile;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#fire(gameworld.GameWorld, engine.gameobject.PointSimple)
     */
    @Override
    public void fire (ObjectCollection world, GameObject target, PointSimple location) {
        if (canFire()) {
                myFiringStrategy.execute(world, target, location, myProjectile);
                timeSinceFire = 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#addBuff(engine.gameobject.units.Buff)
     */
    @Override
    public void addBuff (Buff newBuff) {
        myProjectile.getCollider().addCollisionBehavior(newBuff);
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#getValue()
     */
    @Override
    public double getValue () {
        return tree.getValue();
    };

    @Override
    public double getRange () {
        return myRange.getRange();
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#getFiringRate()
     */
    @Override
    public double getFiringRate () {
        return myFiringRate.getRate();
    }

    @Override
    public void advanceTime(){
        timeSinceFire++;
    }
    /*
     * Utility that we may need in the future
     * private void fireAtEnemyInRange (GameWorld world, PointSimple center) {
     * ArrayList<GameObject> candidates =
     * (ArrayList<GameObject>) world.objectsInRange(myRange, center);
     * // TODO: In bloons, we choose from the candidates using first, last, strong, weak. We could
     * // do something here as well using polymorphism. For now, we just choose a random one.
     * if (!candidates.isEmpty()){
     * myFiringStrategy.execute(world, candidates, center, myProjectile);
     * timeSinceFire = 0;
     * }
     * }
     */

    // TODO: Get the math correct here
    private double firingRateToSeconds () {
        return 60.0 / myFiringRate.getRate();
    }

    private boolean canFire () {
        return timeSinceFire > firingRateToSeconds();
    }

    public List<UpgradeBundle> getNextUpgrades () {
        return tree.getNextUpgrades();
    }

    public void applyUpgrades (UpgradeBundle bundle) {
        bundle.applyUpgrades(upgradables);
        bundle.getParent().updateCurrent(bundle.getParent());
    }
}
