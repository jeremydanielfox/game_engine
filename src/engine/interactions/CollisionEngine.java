package engine.interactions;

import engine.gameobject.labels.Type;


public class CollisionEngine extends ConcreteInteractionEngine {
    public void impartsBuffs (Type buffer, Type buffable) {
        super.put(buffer, buffable, new BuffImparter());
    }
}
