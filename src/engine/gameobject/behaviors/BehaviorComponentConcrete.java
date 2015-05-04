// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

/**
 * Concrete implementation of the BehaviorComponent
 * 
 * @author Jeremy
 *
 */
public class BehaviorComponentConcrete implements BehaviorComponent {
    private BehaviorTracker myBehaviors = new BehaviorTracker();
    private GameObject myGameObject;
    @Override
    public void addOnDeathBehavior (Behavior behavior) {
        myBehaviors.addOnDeath(behavior);
    }

    @Override
    public void clearDeathBehavior () {
        myBehaviors.clearDeath();
    }

    @Override
    public void addEndOfPathBehavior (Behavior behavior) {
        myBehaviors.addEndOfPath(behavior);
    }

    @Override
    public void clearEndOfPathBehavior () {
        myBehaviors.clearEndOfPath();
    }
    
    @Override
    public void onDeath (ObjectCollection world) {
        myBehaviors.onDeath(world, myGameObject);
    }
}
