package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

public class DisappearBehavior extends DeathBehavior{
    public void execute (ObjectCollection world, GameObject object) {
        super.execute(world, object);
        object.clearDeathBehavior();
    }
}
