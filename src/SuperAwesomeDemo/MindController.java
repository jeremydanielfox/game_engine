package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverDirection;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.FriendlyTowerType;
import engine.gameobject.units.WeaponBuff;
import engine.gameobject.units.ZombieBuff;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.Weapon;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.firingstrategy.UserStrategy;

public class MindController extends BasicWeapon{
    
    private static MindController mcInstance;
    private static boolean instanced = false;
    public MindController(GameObject object){
       
        super();
//        if (!(object.getLabel() instanceof EnemyTowerType) )
//            return;
        if (mcInstance == null && !instanced){
            instanced = true;
            mcInstance = new MindController(object);
        }
        setFiringRate(60);//Infinite "Firing Rate check"
        setRange(1000);//Infinite range
        UserStrategy myStrategy = new UserStrategy();
        myStrategy.setFiringRate(.5);//Set Firing Rate here
        myStrategy.setGraphic(object.getGraphic());
        setFiringStrategy(myStrategy);
        GameObject myProjectile = makeProjectile(object);
        setProjectile(myProjectile);
    }
    
    public MindController getInstance(){
        return mcInstance;
    }
    private GameObject makeProjectile(GameObject object){
        GameObject myProjectile = new GameObjectSimple();
        myProjectile.setType(new FriendlyProjectileType());
        Graphic myGraphic = new Graphic(25,25,"/images/brain.png");//TODO: Set graphic here
        myGraphic.setRotator(new RotatorNull());
        myProjectile.setGraphic(myGraphic);
        myProjectile.setPoint(new PointSimple(0, 10000));
        myProjectile.setHealth(new HealthSimple(1));
        myProjectile.setWeapon(new NullWeapon());
        myProjectile.getCollider().addCollisionBehavior(new ZombieBuff(new FriendlyTowerType()));
        Weapon newWeapon;
        if (mcInstance != null){
        newWeapon = getInstance();
        newWeapon.setFiringStrategy(new SingleProjectile());
        newWeapon.setFiringRate(.5);
        }
        else {
            newWeapon = this;
            newWeapon.setFiringStrategy(new SingleProjectile());
            newWeapon.setFiringRate(.5);
        }
        myProjectile.getCollider().addCollisionBehavior(new WeaponBuff(newWeapon));
        MoverDirection myMover = new MoverDirection(new PointSimple(0, 0), 1, 600);
        myProjectile.setMover(myMover);
        return myProjectile;
    }
}
