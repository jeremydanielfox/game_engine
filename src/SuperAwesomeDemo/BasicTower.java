package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.behaviors.SummonBehavior;
import engine.gameobject.test.TowerLabel;
import engine.gameobject.test.bloons.RedBloon;
import engine.gameobject.weapon.NullWeapon;

public class BasicTower extends GameObjectSimple {

    public BasicTower () {
        super();
        setLabel(new TowerLabel());
        Graphic myGraphic = new Graphic(25,25,"/images/ArcaneTower.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        setMover(new MoverDirection());
        SummonBehavior summon = new SummonBehavior();
        summon.addSummon(new RedBloon());
        addOnDeathBehavior(summon);
    }
}
