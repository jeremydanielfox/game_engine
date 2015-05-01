package engine.interactions;

import engine.gameobject.GameObject;


/**
 * Represents when two GameObjects are supposed to not interact.
 * Instantiate this interaction and give to the InteractionEngine.
 * This action works differently than just not defining an interaction between two GameObjects
 * because it will force no interaction to happen, instead of possibly defering the interaction to
 * another type.
 *
 * @author Jeremy
 *
 */
public class NoInteraction extends Interaction {

    @Override
    public void accept (GameObject t, GameObject u) {
    }

}
