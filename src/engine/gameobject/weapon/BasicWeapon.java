package engine.gameobject.weapon;

import java.util.List;
import java.util.stream.Collectors;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.Projectile;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.upgradable.firingrate.FiringRate;
import engine.gameobject.weapon.upgradable.firingrate.FiringRateUpgrade;
import engine.gameobject.weapon.upgradable.range.Range;
import engine.gameobject.weapon.upgradable.range.RangeUpgrade;
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
    protected int timeSinceFire;
    protected Range myRange;
    protected FiringRate myFiringRate;
    protected Buffer myProjectile;
    protected FiringStrategy myFiringStrategy;
    private ClassSet<Upgrade> upgradables;
    protected UpgradeTree tree;

    public BasicWeapon () {
        timeSinceFire = 0;
        myRange = new RangeUpgrade(250);
        myFiringRate = new FiringRateUpgrade(.5);
        myFiringStrategy = new SingleProjectile();
        upgradables = new ClassSet<Upgrade>(new Upgrade[] { myRange,
                                                           myFiringRate });
    }

    @Settable
    public void setRange (double range) {
        myRange = new RangeUpgrade(range);
    }

    public void setFiringStrategy (FiringStrategy newStrategy) {
        myFiringStrategy = newStrategy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#fire(gameworld.GameWorld, engine.gameobject.PointSimple)
     */
    @Override
    public void fire (ObjectCollection world, PointSimple location) {
        if (canFire()) {
            List<GameObject> targets =
                    (List<GameObject>) world.objectsInRange(myRange.getRange(), location);
            List<Buffable> buffables =
                    // targets.stream().filter(p -> p instanceof Buffable &&
                    // p.getPoint().getX()!=location.getX())//This needs to be filtered by team and
                    // object type
                    targets.stream().filter(p -> p instanceof GameObjectSimpleTest)// temporary
                            .map(p -> (Buffable) p)
                            .collect(Collectors.toList());
            if (!buffables.isEmpty()) {
                Buffable target = chooseTarget(buffables);
                myFiringStrategy.execute(world, target, location, myProjectile);
                timeSinceFire = 0;
                return;
            }
        }
        timeSinceFire++;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#addBuff(engine.gameobject.units.Buff)
     */
    @Override
    public void addBuff (Buff newBuff) {
        myProjectile.addCollisionBehavior(newBuff);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * engine.gameobject.weapon.Weaopn#setProjectile(engine.gameobject.weapon.firingstrategy.Projectile
     * )
     */
    @Override
    public void setProjectile (Projectile projectile) {
        myProjectile = projectile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#getRange()
     */
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

    // TODO: In bloons, we choose from the candidates using first, last, strong, weak. We could
    // do something here as well using polymorphism. For now, we just choose a random one.
    private Buffable chooseTarget (List<Buffable> targets) {
        return targets.get(0);
    }

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
