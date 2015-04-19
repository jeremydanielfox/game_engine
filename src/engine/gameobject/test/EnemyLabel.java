package engine.gameobject.test;

import engine.gameobject.labels.LabelConcrete;
import engine.gameobject.labels.SimpleLabel;

public class EnemyLabel extends SimpleLabel {
    private static final String MY_NAME = "Enemy";

    public EnemyLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
