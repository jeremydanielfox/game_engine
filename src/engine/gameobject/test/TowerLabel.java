package engine.gameobject.test;

import engine.gameobject.labels.SimpleLabel;


public class TowerLabel extends SimpleLabel {
    private static final String MY_NAME = "Tower";

    public TowerLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
