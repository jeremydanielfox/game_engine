package engine.interactions;

import musician.MusicianSimple;
import engine.gameobject.GameObject;


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
        // TODO: This isDead check is in two places. Consolidate into the part that calls the
        // interaction engine maybe?
        if (!t.isDead() && t.getPoint().withinRange(u.getPoint(), t.getWeapon().getRange())) {
            MusicianSimple.getInstance().laser();
            t.fire(super.getGameWorld(), u);
        }
    }

}
