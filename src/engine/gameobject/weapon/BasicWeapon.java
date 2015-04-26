package engine.gameobject.weapon;

import java.util.List;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.SetChangeListener;
import javafx.collections.SetChangeListener.Change;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingrate.FiringRate;
import engine.gameobject.weapon.firingrate.FiringRateUpgrade;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
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
    private RangeUpgrade myRange;
    private DoubleProperty rangeProp = new SimpleDoubleProperty();

    private FiringRate myFiringRate;
    private GameObject myProjectile;
    private FiringStrategy myFiringStrategy;
    private UpgradeSet<Upgrade> upgradables;
    private UpgradeTree tree;

    private double value;

    public BasicWeapon () {
        upgradables = new UpgradeSet<>();
        timeSinceFire = 0;
        myFiringStrategy = new SingleProjectile();
    }

    @Override
    public Weapon clone () {
        BasicWeapon clone = new BasicWeapon();
        clone.setFiringRate(myFiringRate.getRate());
        clone.setRange(myRange.getRange());
        clone.setFiringStrategy(myFiringStrategy);
        clone.setProjectile(myProjectile);
        clone.setTree(tree.clone());
        return clone;
    }

    private void initializeUpgrades () {
        Set<Buff> collisionBuffs = myProjectile.getCollider().getCollisionBuffs();
        Set<Buff> explosBuffs = myProjectile.getCollider().getCollisionBuffs();
        upgradables.addAll(collisionBuffs);
        upgradables.addAll(explosBuffs);
        upgradables.addListener((SetChangeListener<Upgrade>) change ->
                syncBuffs(change, collisionBuffs, explosBuffs));
    }

    private void syncBuffs (Change<? extends Upgrade> change,
                            Set<Buff> collisionBuffs,
                            Set<Buff> explosBuffs) {

        Buff buff = (change.wasAdded()) ? (Buff) change.getElementAdded() :
                                       (Buff) change.getElementRemoved();
        switch (buff.getType()) {
            case COLLISION:
                if (change.wasAdded()) {
                    collisionBuffs.add(buff);
                }
                else {
                    collisionBuffs.remove(buff);
                }
                break;
            case EXPLOSION:
                if (change.wasAdded()) {
                    explosBuffs.add(buff);
                }
                else {
                    explosBuffs.remove(buff);
                }
                break;
            default:
                break;
        }
    }

    @Override
    @Settable
    public void setRange (double range) {
        myRange = new RangeUpgrade(range);
        rangeProp.setValue(range);
        upgradables.add(myRange);
        myRange.addObserver(new UpgradeObserver(this::updateRange));
    }

    private void updateRange () {
        myRange = (RangeUpgrade) upgradables.get(myRange);
        rangeProp.setValue(myRange.getRange());
        myRange.addObserver(new UpgradeObserver(this::updateRange));
    }
  
    @Override
    @Settable
    public void setFiringRate (double firingRate) {
        myFiringRate = new FiringRateUpgrade(firingRate);
        upgradables.add(myFiringRate);
        // myFiringRate.addObserver(new UpgradeObserver(this::updateFiringRate));
    }

    private void updateFiringRate () {

    }

    @Override
    @Settable
    public void setFiringStrategy (FiringStrategy newStrategy) {
        myFiringStrategy = newStrategy;
    }

    @Override
    @Settable
    public void setProjectile (GameObject projectile) {
        myProjectile = projectile;
        initializeUpgrades();
    }

    @Settable
    public void setTree (UpgradeTree tree) {
        this.tree = tree;
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

    @Settable
    public void setValue (double value) {
        this.value = value;
    }

    @Override
    public double getValue () {
        return (tree == null) ? value : tree.getValue();
    }

    @Override
    public double getRange () {
        return rangeProp.get();
    }

    public DoubleProperty getRangeProperty () {
        return rangeProp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.gameobject.weapon.Weaopn#getFiringRate()
     */
    @Override
    public double getFiringRate () {
        return ((FiringRate) upgradables.get(myFiringRate)).getRate();
    }

    @Override
    public void advanceTime () {
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

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        return tree.getNextUpgrades();
    }

    @Override
    public void applyUpgrades (UpgradeBundle bundle) {
        bundle.applyUpgrades(upgradables);
        bundle.getParent().updateCurrent(bundle.getParent());
    }
}
