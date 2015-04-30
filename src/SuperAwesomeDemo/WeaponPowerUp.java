package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.HealthSimple;
import engine.gameobject.units.WeaponBuff;
import engine.gameobject.weapon.Weapon;

public class WeaponPowerUp extends GameObjectSimple{
    public WeaponPowerUp(Weapon weapon){
        setHealth(new HealthSimple(Integer.MAX_VALUE));
        getCollider().addCollisionBehavior(new WeaponBuff(weapon));
        setLabel(new PowerupType());
    }
}
