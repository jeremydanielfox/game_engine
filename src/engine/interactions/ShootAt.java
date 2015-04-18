package engine.interactions;

import java.util.function.BiConsumer;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.GameWorld;


/**
 * Takes two GameObjects, a first and a second. Checks to see if the second is in range
 * of the first. If so, the first fires at the second.
 * 
 * @author Jeremy
 *
 */
public class ShootAt extends Interaction {

    @Override
    public void accept (GameObject t, GameObject u) {
        if (t.getPoint().withinRange(u.getPoint(), t.getRange()))
            t.fire(super.getGameWorld());

    }

}
