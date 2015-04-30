package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverUser;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.test.FriendlyTowerType;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;


public class Hero extends GameObjectSimple {
    public Hero (int xcor, int ycor) {
        super();
        setPoint(new PointSimple(xcor, ycor));
        setLabel(new FriendlyTowerType());
        Graphic myGraphic = new Graphic(20, 33.3, "/images/BoxheadHero.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(10));
        Weapon myWeapon = new BasicWeapon();
        myWeapon.setProjectile(new BasicFriendlyProjectile());
        myWeapon.setFiringRate(.5);
        myWeapon.setRange(100);
        setWeapon(myWeapon);
        MoverUser myMover = new MoverUser();
        myMover.setGraphic(myGraphic);
        // myMover.setRange(200);
        setMover(myMover);
        // clearEndOfPathBehavior();
        // this.addEndOfPathBehavior(new PlantBehavior());
    }
}
