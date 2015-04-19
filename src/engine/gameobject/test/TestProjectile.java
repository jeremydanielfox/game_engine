package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.units.DirectDamage;
import engine.gameobject.units.FreezeBuff;
import engine.gameobject.units.PoisonBuff;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.firingstrategy.Projectile;

public class TestProjectile extends GameObjectSimple{

    private int myType;
    
    public TestProjectile(int type){
        super();
        myType = type;

        setGraphic(new Graphic(20, 20, "robertDuvall.jpg"));
        setPoint(new PointSimple(300,300));
        setHealth(new HealthSimple(1));
        setMover(new MoverDirection(getPoint(), 3.3, 250));
        setWeapon(new NullWeapon());
        setLabel(new ProjectileLabel());
        if (type == 1 || type == 4){
            getCollider().addCollisionBehavior(new DirectDamage(4));
        }
        if (type == 2){
            getCollider().addCollisionBehavior(new FreezeBuff(30));
        }
        if (type == 3){
            getCollider().addExplosionBuff(new PoisonBuff(1000, 4));
            getCollider().addExplosionBuff(new FreezeBuff(30));
            getCollider().setExplosionRadius(100);
            setHealth(new HealthSimple(0));
        }
        if (type == 4){
            getCollider().addCollisionBehavior(new DirectDamage(1));
            setMover(new MoverDirection(getPoint(), 4, 90));
        }
    }
    
    public GameObjectSimple clone(){
        return new TestProjectile(myType);
    }
}