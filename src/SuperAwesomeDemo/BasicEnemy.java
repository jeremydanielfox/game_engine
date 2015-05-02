package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverPath;
import engine.gameobject.test.EnemyTowerType;
import gameworld.GameWorld;


public class BasicEnemy extends GameObjectSimple {
    public BasicEnemy (GameWorld gw) {
        super();
        setType(new EnemyTowerType());
        Graphic myGraphic = new Graphic(47, 28, "/images/BoxHeadBasicEnemy.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new MeleeWeapon(this));
        setMover(new MoverPath(gw.getPath(), .3));
    }
  
}
