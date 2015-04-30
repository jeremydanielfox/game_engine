package engine.gameobject.test;

import engine.gameobject.labels.SimpleType;


public class FriendlyTowerType extends SimpleType {
    private static final String MY_NAME = "Tower";

    public FriendlyTowerType () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
