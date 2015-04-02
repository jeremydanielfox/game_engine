package engine.gameobject;

import java.util.Collection;


/**
 * 
 * @author Jeremy
 *
 */
public class ConcreteWeapon implements Weapon {
    private double myRange;
    private double myFiringRate;
    private Collection<GameObject> myProjectiles;

    @Override
    public double getRange () {
        return myRange;
    }

    @Override
    public double getFiringRate () {
        return myFiringRate;
    }

    @Override
    public Collection<GameObject> getProjectiles () {
        return myProjectiles;
    }

}
