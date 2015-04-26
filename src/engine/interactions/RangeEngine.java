package engine.interactions;

import engine.gameobject.labels.Type;


public class RangeEngine extends ConcreteInteractionEngine {

    public void shootsAt (Type first, Type second) {
        super.put(first, second, new ShootAt());
    }

    public void doesntShootAt (Type first, Type second) {
        super.put(first, second, new NoInteraction());
    }
}
