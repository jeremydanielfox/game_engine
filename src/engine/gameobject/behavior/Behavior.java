package engine.gameobject.behavior;

import engine.gameobject.GameObject;

/**
 * Actions associated with a weapon. They will be applied every time a weapon is used on the 
 * intended target of a GameObject's attack. 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public interface Behavior {
    
    public void apply(GameObject target);
    
    

}
