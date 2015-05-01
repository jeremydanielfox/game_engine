package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.MultipleProjectile;
import engine.gameobject.weapon.firingstrategy.UserStrategy;

public class Shotgun extends BasicWeapon{
    public Shotgun(GameObject object){
        super();
        setFiringRate(60);//Infinite "Firing Rate check"
        setRange(1000);//Infinite range
        UserStrategy myStrategy = new UserStrategy();
        myStrategy.setFiringRate(1);//Set Firing Rate here
        myStrategy.setGraphic(object.getGraphic());
        myStrategy.setFiringNumber(8);
        setFiringStrategy(myStrategy);//TODO: Make this shoot multiple
        GameObject myProjectile = new BasicFriendlyProjectile(3, 1.5, 200);//Set projectile's basic properties here
        //Add extra projectile properties here, such as graphics or other buffs
        setProjectile(myProjectile);
    }
    
}
