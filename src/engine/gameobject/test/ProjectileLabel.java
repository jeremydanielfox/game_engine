package engine.gameobject.test;

import engine.gameobject.labels.SimpleType;


public class ProjectileLabel extends SimpleType {
    private static final String MY_NAME = "Projectile";

    public ProjectileLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
