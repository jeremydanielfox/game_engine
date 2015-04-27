package engine.gameobject.weapon;

import java.util.List;
import java.util.Set;
import musician.MusicianSimple;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.SetChangeListener;
import javafx.collections.SetChangeListener.Change;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingrate.FiringRate;
import engine.gameobject.weapon.firingrate.FiringRateUpgrade;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.gameobject.weapon.upgradetree.UpgradeTreeSimple;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import gameworld.ObjectCollection;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 *
 * @author Nathan Prabhu and Danny Oh
 *
 */

@Settable
public class BasicWeapon implements Weapon{
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
        myFiringRate = new FiringRateUpgrade();
        myRange = new RangeUpgrade();
        myProjectile = new GameObjectSimple();
        tree = new UpgradeTreeSimple();
        value = 0;
    }

    @Override
    public Weapon clone () {
        BasicWeapon clone = new BasicWeapon();
        clone.setFiringRate(myFiringRate.getRate());
        clone.setRange(myRange.getRange());
        clone.setFiringStrategy(myFiringStrategy);
        clone.setProjectile(myProjectile);
        clone.setTree(tree.clone());
        clone.setValue(value);
        return clone;
    }

    //TODO: make collisionBuffs unmodifable somehow...
    private void initializeUpgrades () {
        Set<Buff> collisionBuffs = myProjectile.getCollider().getCollisionBuffs();
        Set<Buff> explosBuffs = myProjectile.getCollider().getCollisionBuffs();
        upgradables.addAll(collisionBuffs);
        upgradables.addAll(explosBuffs);
        upgradables.addListener((SetChangeListener<Upgrade>) change ->
                syncBuffs(change, collisionBuffs, explosBuffs));
    }

    /**
     * Adds new buffs to either collision or explosion buff set, if added to upgrade set.
     * 
     * @param change
     * @param collisionBuffs
     * @param explosBuffs
     */
    private void syncBuffs (Change<? extends Upgrade> change,
                            Set<Buff> collisionBuffs,
                            Set<Buff> explosBuffs) {

        Upgrade upg = change.wasAdded() ? change.getElementAdded() :
                                       change.getElementRemoved();
        switch (upg.getType()) {
            case COLLISION:
                if (change.wasAdded()) {
                    collisionBuffs.add((Buff) upg);
                }
                else {
                    collisionBuffs.remove((Buff) upg);
                }
                break;
            case EXPLOSION:
                if (change.wasAdded()) {
                    explosBuffs.add((Buff) upg);
                }
                else {
                    explosBuffs.remove((Buff) upg);
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

//    @Settable - commented out since we can't handle trees for now.
    public void setTree (UpgradeTree tree) {
        this.tree = tree;
    }

    /*
     * (non-Javadoc)d
     * 
     * @see engine.gameobject.weapon.Weaopn#fire(gameworld.GameWorld, engine.gameobject.PointSimple)
     */
    @Override
    public void fire (ObjectCollection world, GameObject target, PointSimple location) {
        if (canFire()) {
            MusicianSimple.getInstance().laser();
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
        double treeValue = (tree == null) ? 0 : tree.getValue();
        return value + treeValue;
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

    // TODO: Get the math correct here
    private double firingRateToSeconds () {
        return 60.0 / getFiringRate();
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
