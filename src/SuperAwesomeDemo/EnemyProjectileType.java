package SuperAwesomeDemo;

import engine.gameobject.labels.SimpleType;

public class EnemyProjectileType extends SimpleType {
    private static final String MY_NAME = "EnemyProjectile";

    public EnemyProjectileType () {
        setName();
    }

    private void setName () {
         this.setName(MY_NAME);
    }
}
