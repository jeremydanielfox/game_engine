package engine.interactions;

import gameobject.GameObject;
import java.util.function.BiConsumer;


/**
 * 
 * @author Jeremy
 *
 */
public interface InteractionEngine {
    public void interact (GameObject first, GameObject second);

    public void put (GameObject first,
                     GameObject second,
                     BiConsumer<GameObject, GameObject> consumer);
}
