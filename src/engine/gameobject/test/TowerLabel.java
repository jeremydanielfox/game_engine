package engine.gameobject.test;

import engine.gameobject.labels.LabelConcrete;


public class TowerLabel extends LabelConcrete {
    private static final String MY_NAME = "Tower";

    public TowerLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
