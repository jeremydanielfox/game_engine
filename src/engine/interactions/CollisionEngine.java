package engine.interactions;

import engine.gameobject.labels.Label;


public class CollisionEngine extends ConcreteInteractionEngine {
    public void impartsBuffs (Label buffer, Label buffable) {
        super.put(buffer, buffable, new BuffImparter());
    }
}
