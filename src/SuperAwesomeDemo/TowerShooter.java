package SuperAwesomeDemo;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.UserStrategy;


public class TowerShooter extends BasicWeapon {
    public TowerShooter (GameObject object) {
        super();
        setFiringRate(60);// Infinite "Firing Rate check"
        setRange(1000);// Infinite range
        UserStrategy myStrategy = new UserStrategy();
        myStrategy.setFiringRate(1);// Set Firing Rate here
        myStrategy.setGraphic(object.getGraphic());
        setFiringStrategy(myStrategy);// TODO: Make this shoot multiple
        GameObject myProjectile = new BasicTower();// Towers that shoot projectiles
        // Add extra projectile properties here, such as graphics or other buffs
        setProjectile(myProjectile);
    }
}
