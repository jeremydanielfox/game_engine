package engine.gameobject.weapon;

import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.upgradable.FiringRate;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradable.behavior.Behavior;
import engine.gameobject.weapon.upgradable.range.Range;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 * @author Nathan Prabhu
 *
 */
public abstract class Weapon {
    private int timeSinceFire;
    private Range myRange;
    private FiringRate myFiringRate;
    private List<Buff> myBehaviors;
    private FiringStrategy myFiringStrategy;
    Map<Class<? extends Upgradable>, Upgradable> upgradables;
    private UpgradeTree tree;
    
    /**
     * Attacks targets, inflicting the appropriate damage upon them. 
     * @param targets
     */
    public void fire(){
        if(canFire()){
            myFiringStrategy.execute(GameObject target);
        }
    }
    
    /**
     * Adds a behavior to the given weapon. 
     * @param behavior
     */
    public void addBuff(Buff newBuff){
        
    }
  
    
    /**
     * The value at which this weapon can be sold to the shop
     * @return
     */
    public double getValue (){
        return tree.getValue();
    }; 
    
    private double fireAtEnemyInRange(){
        
    }
    //TODO: Get the math correct here
    private double firingRateToSeconds(){
        return 60.0/myFiringRate.getRate();
    }
    
    private boolean canFire(){
        if (timeSinceFire > firingRateToSeconds())
            return true;
        return false;
    }
}
