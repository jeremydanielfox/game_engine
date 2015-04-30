package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.NullWeapon;

public class Barrel extends GameObjectSimple {
    public Barrel(){
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        setGraphic(new Graphic());//TODO: Set graphics stuff here
        getCollider().addExplosionBuff(new DamageBuff(3));
        getCollider().setExplosionRadius(30);
    }
}
