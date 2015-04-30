package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.test.EnemyTowerType;
import gameworld.GameWorld;


public class Devil extends GameObjectSimple {
    public Devil (GameWorld gw) {
        super();
        setLabel(new EnemyTowerType());
        Graphic myGraphic = new Graphic(58, 35, "/images/Devil.gif");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(5));
        setWeapon(new FireballShooter());
        setMover(new MoverPath(gw.getPath(), 1));
    }
}
