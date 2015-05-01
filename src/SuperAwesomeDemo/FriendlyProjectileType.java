package SuperAwesomeDemo;

import engine.gameobject.labels.SimpleType;

public class FriendlyProjectileType extends SimpleType {
    private static final String MY_NAME = "TowerProjectile";

    public FriendlyProjectileType () {
        setName();
    }

    private void setName () {
         this.setName(MY_NAME);
    }
}
