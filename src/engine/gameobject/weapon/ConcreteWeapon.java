package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import engine.gameobject.GameObject;
import engine.gameobject.behavior.Behavior;


/**
 * 
 * @author Nathan Prabhu
 *
 */
public class ConcreteWeapon implements Weapon {
    private double myRange;
    private double myFiringRate;
    private List<Behavior> myBehaviors;
    private GameObject myProjectile;

    public ConcreteWeapon (double range,
                           double firingRate,
                           GameObject projectile,
                           Behavior ... behaviors) {
        myRange = range;
        myFiringRate = firingRate;
        myProjectile = projectile;
        myBehaviors = new ArrayList<>(Arrays.asList(behaviors));
    }

    @Override
    public double getRange () {
        return myRange;
    }

    @Override
    public double getFiringRate () {
        return myFiringRate;
    }

    public GameObject getProjectile () {
        return myProjectile;
    }

    @Override
    public void applyBehaviors (Consumer<Behavior> action) {
        myBehaviors.forEach(action);
    }

    @Override
    public void addRange (double value) {
        myRange+=value;
        
    }

}
