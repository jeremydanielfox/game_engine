package engine.gameobject.test;

import engine.gameobject.labels.LabelConcrete;

public class EnemyLabel extends LabelConcrete {
    private static final String MY_NAME = "Enemy";

    public EnemyLabel () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
