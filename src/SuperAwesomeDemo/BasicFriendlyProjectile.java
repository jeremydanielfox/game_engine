package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.NullWeapon;


public class BasicFriendlyProjectile extends GameObjectSimple {
    public BasicFriendlyProjectile () {
        super();
        setLabel(new FriendlyProjectileType());
        Graphic myGraphic = new Graphic(25,25,"/images/robertDuvall.jpg");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        MoverDirection myMover = new MoverDirection();
        myMover.setRange(100);
        getCollider().addCollisionBehavior(new DamageBuff(1));
        setMover(myMover);
    }
}
