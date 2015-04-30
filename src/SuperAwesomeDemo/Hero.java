package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverUser;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.test.FriendlyTowerType;


public class Hero extends GameObjectSimple {
    public Hero (int xcor, int ycor) {
        super();
        setLabel(new FriendlyTowerType());
        Graphic myGraphic = new Graphic(47, 28, "/images/BoxheadHero.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(10));
//        Weapon myWeapon = new BasicWeapon();
//        myWeapon.setProjectile(new BasicFriendlyProjectile());
//        myWeapon.setFiringRate(.5);
//        myWeapon.setRange(100);
        setWeapon(new TowerShooter(this));
//        setWeapon(new Shotgun(this));
        MoverUser myMover = new MoverUser(10);
        myMover.setGraphic(myGraphic);
        // myMover.setRange(200);
        setMover(myMover);
        // clearEndOfPathBehavior();
        // this.addEndOfPathBehavior(new PlantBehavior());
    }
}
