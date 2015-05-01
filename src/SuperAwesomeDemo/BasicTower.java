package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.behaviors.PlantBehavior;
import engine.gameobject.test.FriendlyTowerType;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;


public class BasicTower extends GameObjectSimple {

    public BasicTower () {
        super();
        setLabel(new FriendlyTowerType());
        Graphic myGraphic = new Graphic(30, 48, "/images/Cool_Turret.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        Weapon myWeapon = new BasicWeapon();
        myWeapon.setProjectile(new BasicFriendlyProjectile());
        myWeapon.setFiringRate(.5);
        myWeapon.setRange(100);
        setWeapon(myWeapon);
        MoverDirection myMover = new MoverDirection();
        myMover.setRange(200);
        setMover(myMover);
        clearEndOfPathBehavior();
        this.addEndOfPathBehavior(new PlantBehavior());
    }
}
