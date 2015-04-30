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

public class RocketLauncher extends BasicWeapon {
    public RocketLauncher(GameObject object){
        super();
        setFiringRate(60);//Infinite "Firing Rate check"
        setRange(1000);//Infinite range
        UserStrategy myStrategy = new UserStrategy();
        myStrategy.setFiringRate(.5);//Set Firing Rate here
        myStrategy.setGraphic(object.getGraphic());
        setFiringStrategy(myStrategy);
        GameObject myProjectile = makeProjectile();
        setProjectile(myProjectile);
    }
    
    private GameObject makeProjectile(){
        GameObject myProjectile = new GameObjectSimple();
        myProjectile.setLabel(new FriendlyProjectileType());
        Graphic myGraphic = new Graphic(25,25,"/images/Missile.png");//TODO: Set graphic here
        myGraphic.setRotator(new RotatorNull());
        myProjectile.setGraphic(myGraphic);
        myProjectile.setPoint(new PointSimple(0, 10000));
        myProjectile.setHealth(new HealthSimple(1));
        myProjectile.setWeapon(new NullWeapon());
        myProjectile.getCollider().addExplosionBuff(new DamageBuff(3));//damage set
        myProjectile.getCollider().setExplosionRadius(100);//radius set
        MoverDirection myMover = new MoverDirection(new PointSimple(0, 0), 1, 600);
        myProjectile.setMover(myMover);
        return myProjectile;
    }
}
