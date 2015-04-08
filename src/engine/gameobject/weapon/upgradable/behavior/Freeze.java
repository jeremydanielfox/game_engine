package engine.gameobject.weapon.upgradable.behavior;

import engine.gameobject.GameObject;

/**
 * Allows enemy to be frozen for a specific amount of time
 * @author Nathan Prabhu
 *
 */
public class Freeze implements Behavior {
    
    public void apply(GameObject target){
        target.setSpeed(0);
        //target.setGraphics...
    }
}
