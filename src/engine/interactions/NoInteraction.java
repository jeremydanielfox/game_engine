package engine.interactions;

import engine.gameobject.GameObject;


/**
 * Represents when two GameObjects are supposed to not interact.
 * Instantiate this interaction and give to the InteractionEngine
 * 
 * @author Jeremy
 *
 */
public class NoInteraction extends Interaction {

    @Override
    public void accept (GameObject t, GameObject u) {
    }

}
