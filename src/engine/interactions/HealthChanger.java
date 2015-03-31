package engine.interactions;

import gameobject.GameObject;
import java.util.function.BiConsumer;


/**
 * This preset interaction can be used in the InteractionEngine to define an
 * interaction between two GameObjects, changing the health of one or both.
 * 
 * @author Jeremy
 *
 */
public class HealthChanger implements BiConsumer<GameObject, GameObject> {
    private double first = 0;
    private double second = 0;

    @Override
    public void accept (GameObject t, GameObject u) {
        t.changeHealth(first);
        u.changeHealth(second);
    }

}
