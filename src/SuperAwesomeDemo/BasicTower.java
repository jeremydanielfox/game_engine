package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.behaviors.PlantBehavior;
import engine.gameobject.test.TowerLabel;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;


public class BasicTower extends GameObjectSimple {

    public BasicTower () {
        super();
        setLabel(new TowerLabel());
        Graphic myGraphic = new Graphic(25, 25, "/images/ArcaneTower.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        Weapon myWeapon = new BasicWeapon();
        myWeapon.setProjectile(new BasicProjectile());
        setWeapon(myWeapon);
        setMover(new MoverDirection());
        this.addEndOfPathBehavior(new PlantBehavior());
    }
}
