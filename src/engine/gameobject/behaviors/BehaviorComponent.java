// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.behaviors;

import gameworld.ObjectCollection;


/**
 * This is the interface for the component that holds all of the GameObject's behaviors
 * 
 * @author Jeremy
 *
 */
public interface BehaviorComponent {
    /**
     * Adds an ondeath behavior to the BehaviorComponent
     * 
     * @param behavior
     */
    public void addOnDeathBehavior (Behavior behavior);

    /**
     * Clears the ondeath behaviors from the behaviorComponent
     */
    public void clearDeathBehavior ();

    /**
     * Adds an end of path behavior to the behaviorComponent
     * 
     * @param behavior
     */
    public void addEndOfPathBehavior (Behavior behavior);

    /**
     * Clears the end of path behaviors from the behaviorComponent
     */
    public void clearEndOfPathBehavior ();

    /**
     * Denotes the onDeath behavior for the component
     * 
     * @param world
     */
    public void onDeath (ObjectCollection world);
}
