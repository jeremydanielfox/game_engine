package engine.gameobject.test;

import engine.gameobject.labels.SimpleType;


public class TowerLabel extends SimpleType {
    private static final String MY_NAME = "Tower";

    public TowerLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
