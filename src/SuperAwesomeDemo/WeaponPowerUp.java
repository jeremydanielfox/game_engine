package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.units.WeaponBuff;
import engine.gameobject.weapon.Weapon;

public class WeaponPowerUp extends GameObjectSimple{
    public WeaponPowerUp(Weapon weapon, Graphic graphic){
        setHealth(new HealthSimple(Integer.MAX_VALUE));
        getCollider().addCollisionBehavior(new WeaponBuff(weapon));
        setType(new PowerupType());
        setGraphic(graphic);
    }
}
