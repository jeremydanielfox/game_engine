package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

public interface Behavior {

    /**
     * Executes the behavior
     * @param world world the behavior is happening in 
     * @param object The object performing the behavior
     */
    public void execute(ObjectCollection world, GameObject object);
}
