package engine.interactions;

import java.util.function.BiConsumer;
import engine.gameobject.GameObject;
import gameworld.GameWorld;


/**
 * Represents the base of all the interactions that can be placed into the interaction engine.
 * Contains a copy of the current GameWorld.
 * 
 * @author Jeremy
 *
 */
public abstract class Interaction implements BiConsumer<GameObject, GameObject> {
    private GameWorld myWorld;

    protected GameWorld getGameWorld () {
        return myWorld;
    }

    protected void setGameWorld (GameWorld world) {
        myWorld = world;
    }
}
