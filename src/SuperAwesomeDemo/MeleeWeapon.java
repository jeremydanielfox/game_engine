package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.firingstrategy.UserStrategy;

public class MeleeWeapon extends BasicWeapon{
    public MeleeWeapon(GameObject object){
        super();
        setFiringRate(1);//Melee attack 1/sec
        setRange(20);//Super Small Range
        GameObject myProjectile = makeProjectile();
        setProjectile(myProjectile);
    }
    
    private GameObject makeProjectile(){
        GameObject myProjectile = new GameObjectSimple();
        myProjectile.setLabel(new EnemyProjectileType());
        Graphic myGraphic = new Graphic(1, 1,"/images/robertDuvall.jpg");//TODO: Set graphic here
        myGraphic.setRotator(new RotatorNull());
        myProjectile.setGraphic(myGraphic);
        myProjectile.setPoint(new PointSimple(0, 10000));
        myProjectile.setHealth(new HealthSimple(0));
        myProjectile.setWeapon(new NullWeapon());
        myProjectile.getCollider().addCollisionBehavior(new DamageBuff(3));//damage set
        myProjectile.setMover(new MoverDirection(new PointSimple(), 5, 30));
        return myProjectile;
    }
}
