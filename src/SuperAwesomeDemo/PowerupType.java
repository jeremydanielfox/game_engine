package SuperAwesomeDemo;

import engine.gameobject.labels.SimpleType;


public class PowerupType extends SimpleType {

    private static final String MY_NAME = "Powerup";

    public PowerupType () {
        setName();
    }

    private void setName () {
        this.setName(MY_NAME);
    }
}
