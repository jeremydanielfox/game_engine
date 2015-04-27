package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.units.freeze.FreezeBuff;
import engine.gameobject.units.poison.PoisonBuff;
import engine.gameobject.weapon.NullWeapon;


public class TestProjectile extends GameObjectSimple {

    private int myType;

    public TestProjectile (int type) {
        super();
        myType = type;
        setGraphic(new Graphic(20, 20, "robertDuvall.jpg"));
        setPoint(new PointSimple(300, 300));
        setHealth(new HealthSimple(1));
        setMover(new MoverDirection(getPoint(), 2.5, 250));
        setWeapon(new NullWeapon());
        setLabel(new ProjectileLabel());
        if (type == 0 || type == 2 || type == 4) {
            getCollider().addCollisionBehavior(new DamageBuff(4));
            //getCollider().addCollisionBehavior(new FreezeBuff(120));
        }
        if (type == 3) {
            getCollider().addExplosionBuff(new PoisonBuff(1000, 4));
            getCollider().addExplosionBuff(new FreezeBuff(30));
            getCollider().setExplosionRadius(100);
            setHealth(new HealthSimple(0));
        }
        if (type == 4) {
            setMover(new MoverDirection(getPoint(), 1, 90));
        }
        if (type == 5){
            setWeapon(new TestWeapon(4, null));
            setLabel(new TowerLabel());
        }
    }

}
