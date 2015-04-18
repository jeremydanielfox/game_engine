package engine.interactions;

import engine.gameobject.GameObject;

/**
 * Checks to see if two nodes intersect. If they do, the first node imparts buffs onto the second node.
 * @author Jeremy
 *
 */
public class BuffImparter extends Interaction {

    @Override
    public void accept (GameObject buffer, GameObject buffable) {
        if (buffer.getGraphic().getNode().getBoundsInParent()
                .intersects(buffable.getGraphic().getNode().getBoundsInParent()))
            ;
    }

}
