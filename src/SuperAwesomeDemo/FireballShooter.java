package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.units.poison.PoisonBuff;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.NullWeapon;


public class FireballShooter extends BasicWeapon {

    public FireballShooter () {
        super();
        setFiringRate(.5);//Infinite "Firing Rate check"
        setRange(200);//Infinite range
        GameObject myProjectile = makeFireball();//Set projectile's basic properties here
        //Add extra projectile properties here, such as graphics or other buffs
        setProjectile(myProjectile);
    }

    private GameObject makeFireball () {
        GameObjectSimple gos = new GameObjectSimple();
        gos.setLabel(new EnemyProjectileType());
        Graphic myGraphic = new Graphic(10, 10, "/images/Fireball.png");
        myGraphic.setRotator(new RotatorNull());
        gos.setGraphic(myGraphic);
        gos.setHealth(new HealthSimple(1));
        gos.setWeapon(new NullWeapon());
        MoverDirection myMover = new MoverDirection(new PointSimple(0, 0), .5, 200); // infinite //
                                                                                      // distance
        gos.getCollider().addCollisionBehavior(new DamageBuff(2));
        gos.getCollider().addCollisionBehavior(new PoisonBuff());
        gos.setMover(myMover);
        return gos;
    }
}
