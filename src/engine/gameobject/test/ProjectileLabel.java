package engine.gameobject.test;

import engine.gameobject.labels.LabelConcrete;

public class ProjectileLabel extends LabelConcrete {
    private static final String MY_NAME = "Projectile";

    public ProjectileLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
