package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.upgradable.FiringRate;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradable.behavior.Behavior;
import engine.gameobject.weapon.upgradable.range.Range;
import engine.gameobject.weapon.upgradetree.UpgradeTree;


/**
 * 
 * @author Nathan Prabhu
 *
 */
public class WeaponSimple extends Weapon {
    private Range myRange;
    private FiringRate myFiringRate;
    private List<Behavior> myBehaviors;
    private FiringStrategy myFiringStrategy;
    Map<Class<? extends Upgradable>, Upgradable> upgradables;
    private UpgradeTree tree;
    

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

//    @Override
//    public void attack (GameObject... targets) {
//        Arrays.asList(targets).forEach(target -> {
//            myBehaviors.forEach(behavior -> behavior.apply(target));
//        });
//    }
//    
//    @Override
//    public void addBehavior (Behavior behavior) {
//        // add behavior if it doesn't exist, otherwise upgrade existing one 
//    }

    @Override 
    public double getValue () {
        return tree.getValue();
    }
}
