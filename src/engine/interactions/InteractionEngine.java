package engine.interactions;

import java.util.function.BiConsumer;
import engine.gameobject.GameObject;
import engine.gameobject.labels.Label;


/**
 * 
 * @author Jeremy
 *
 */
public interface InteractionEngine {
    public void interact (GameObject first, GameObject second);

    public void put (Label first,
                     Label second,
                     BiConsumer<GameObject, GameObject> consumer);
}
