package engine.interactions;

import engine.gameobject.labels.Label;

public class RangeEngine extends ConcreteInteractionEngine {

    public void shootsAt(Label first, Label second)      {
        super.put(first, second, new ShootAt());
    }
    
    public void doesntShootAt(Label first, Label second) {
        super.put(first, second, new NoInteraction());
    }
}
