package engine.gameobject.weapon;

import java.util.function.Consumer;
import engine.gameobject.GameObject;
import engine.gameobject.behavior.Behavior;


/**
 * 
 * @author Nathan Prabhus
 *
 */
public interface Weapon {
    public double getRange ();

    public double getFiringRate ();
    
    public GameObject getProjectile ();

    public void applyBehaviors (Consumer<Behavior> action); 
    
}
