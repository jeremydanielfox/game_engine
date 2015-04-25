package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

public class ExplosionBehavior implements Behavior{
    public void execute (ObjectCollection world, GameObject object) {
        object.explode(world);
    }
}
