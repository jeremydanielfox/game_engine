package engine.interactions;

import java.util.function.BiConsumer;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.GameWorld;


public class ShootAt extends Interaction {

    @Override
    public void accept (GameObject t, GameObject u) {
        if (t.getPoint().withinRange(u.getPoint(), t.getRange()))
            t.fire(super.getGameWorld());

    }

}
