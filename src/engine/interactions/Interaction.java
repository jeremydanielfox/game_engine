package engine.interactions;

import java.util.function.BiConsumer;
import engine.gameobject.GameObject;
import gameworld.GameWorld;


public abstract class Interaction implements BiConsumer<GameObject, GameObject> {
    private GameWorld myWorld;

    protected GameWorld getGameWorld () {
        return myWorld;
    }

    protected void setGameWorld (GameWorld world) {
        myWorld = world;
    }
}
