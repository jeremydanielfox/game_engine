package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.behaviors.SummonBehavior;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverPath;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.bloons.RedBloon;
import gameworld.GameWorld;


public class Devil extends GameObjectSimple {
    public Devil (GameWorld gw) {
        super();
        setType(new EnemyTowerType());
        Graphic myGraphic = new Graphic(58, 35, "/images/Devil.gif");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(5));
        setWeapon(new FireballShooter());
        setMover(new MoverPath(gw.getPath(), .5));
        SummonBehavior summon = new SummonBehavior();
        summon.addSummon(new BasicEnemy(gw));
        addOnDeathBehavior(summon);
    }
}
