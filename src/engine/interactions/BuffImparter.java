package engine.interactions;

import engine.gameobject.GameObject;


public class BuffImparter extends Interaction {

    @Override
    public void accept (GameObject t, GameObject u) {
        // TODO Auto-generated method stub
        if (t.getGraphic().getNode().getBoundsInParent()
                .intersects(u.getGraphic().getNode().getBoundsInParent()))
            ;
    }

}
