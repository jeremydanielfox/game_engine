package engine.gameobject.test;

import engine.gameobject.labels.SimpleType;


public class EnemyLabel extends SimpleType {
    private static final String MY_NAME = "Enemy";

    public EnemyLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
