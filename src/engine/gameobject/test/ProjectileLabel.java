package engine.gameobject.test;

import engine.gameobject.labels.SimpleLabel;


public class ProjectileLabel extends SimpleLabel {
    private static final String MY_NAME = "Projectile";

    public ProjectileLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
