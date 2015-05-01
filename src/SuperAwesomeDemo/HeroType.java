package SuperAwesomeDemo;

import engine.gameobject.labels.SimpleType;
import engine.gameobject.test.FriendlyTowerType;

public class HeroType extends SimpleType { private static final String MY_NAME = "Hero";

public HeroType () {
    setName();
    this.setSuperType(new FriendlyTowerType());
}

private void setName () {
     this.setName(MY_NAME);
}
}
