package engine.gameobject.interactions;

import engine.gameobject.types.Type;


public class CollisionEngine extends ConcreteInteractionEngine {
    public void impartsBuffs (Type buffer, Type buffable) {
        super.put(buffer, buffable, new BuffImparter());
    }
}
