package SuperAwesomeDemo;

import engine.gameobject.labels.SimpleType;

public class NeutralGameType extends SimpleType {
    private static final String MY_NAME = "Neutral";

    public NeutralGameType () {
        setName();
    }

    private void setName () {
         this.setName(MY_NAME);
    }
}
