package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverNull;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.NullWeapon;
import engine.shop.ShopTagSimple;


public class Barrel extends GameObjectSimple {
    public Barrel () {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setName("Barrel");
        shopTag.setDescription("Blow people up!");
        shopTag.setShopGraphic(new Graphic(90, 60, "/images/Barrel.png"));
        setShopTag(shopTag);
        setType(new NeutralGameType());
        Graphic myGraphic = new Graphic(47, 28, "/images/Barrel.png");
        myGraphic.setRotator(new RotatorNull());
        this.getCollider().addExplosionBuff(new DamageBuff(5));
        this.getCollider().setExplosionRadius(100);
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        setMover(new MoverNull());
    }
}
