package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
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
    
    public MindController(GameObject object){
        super();
        if (mcInstance == null){
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
        myProjectile.setLabel(new FriendlyProjectileType());
        Graphic myGraphic = new Graphic(25,25,"/images/brain.png");//TODO: Set graphic here
        myGraphic.setRotator(new RotatorNull());
        myProjectile.setGraphic(myGraphic);
        myProjectile.setPoint(new PointSimple(0, 10000));
        myProjectile.setHealth(new HealthSimple(1));
        myProjectile.setWeapon(new NullWeapon());
        myProjectile.getCollider().addCollisionBehavior(new ZombieBuff(new FriendlyTowerType()));
        Weapon newWeapon = getInstance();
        newWeapon.setFiringStrategy(new SingleProjectile());
        newWeapon.setFiringRate(2);
        myProjectile.getCollider().addCollisionBehavior(new WeaponBuff(newWeapon));
        MoverDirection myMover = new MoverDirection(new PointSimple(0, 0), 1, 600);
        myProjectile.setMover(myMover);
        return myProjectile;
    }
}
