package engine.gameobject.test;

import engine.gameobject.types.SimpleType;


public class EnemyTowerType extends SimpleType {
    private static final String MY_NAME = "Enemy";

    public EnemyTowerType () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
