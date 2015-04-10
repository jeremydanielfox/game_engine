package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.upgradable.FiringRate;
import engine.gameobject.weapon.upgradable.behavior.Behavior;
import engine.gameobject.weapon.upgradable.range.Range;
import engine.gameobject.weapon.upgradable.range.RangeUpgrade;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.gameobject.weapon.upgradetree.UpgradeTreeSimple;


/**
 * 
 * @author Nathan Prabhu
 *
 */
@Settable
public class WeaponSimple extends Weapon {
    private Range myRange;
    private FiringRate myFiringRate;
    private List<Behavior> myBehaviors;
    private FiringStrategy myFiringStrategy;
    Map<Class<? extends Upgrade>, Upgrade> upgradables;
    private UpgradeTree tree;

    public WeaponSimple () {
        myRange = new RangeUpgrade();
        //myFiringRate = new FiringRate();
        myBehaviors = new ArrayList<Behavior>();
        myFiringStrategy = new SingleProjectile();
        upgradables = new HashMap<>();
        tree = new UpgradeTreeSimple();
    }

    public WeaponSimple (Range range,
                         FiringRate firingRate,
                         FiringStrategy firingStrategy,
                         Behavior ... behaviors) {
        myRange = range;
        myFiringRate = firingRate;
        myFiringStrategy = firingStrategy;
        myBehaviors = new ArrayList<>(Arrays.asList(behaviors));
    }

    @Override
    public double getRange () {
        return myRange.getRange();
    }

    @Override
    public double getFiringRate () {
        return myFiringRate.getRate();
    }

    public FiringStrategy getFiringStrategy () {
        return myFiringStrategy;
    }

    @Override
    public double getValue () {
        return tree.getValue();
    }

    @Settable
    public void setRange (Range range) {
        myRange = range;
    }

    @Settable
    public void setFiringRate (FiringRate firingRate) {
        myFiringRate = firingRate;
    }

    @Settable
    public void setFiringStrategy (FiringStrategy firingStrategy) {
        myFiringStrategy = firingStrategy;
    }

    @Settable
    public void setBehaviors (List<Behavior> behaviors) {
        myBehaviors = behaviors;
    }
}
