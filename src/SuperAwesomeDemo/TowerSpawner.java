package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverNull;
import engine.gameobject.test.FriendlyTowerType;
import engine.gameobject.weapon.BasicWeapon;
import engine.shop.ShopTagSimple;


public class TowerSpawner extends GameObjectSimple {
    public TowerSpawner () {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setDescription("Master Tower Spawer");
        shopTag.setShopGraphic(new Graphic(40, 40, "/images/pentagram.png"));
        setShopTag(shopTag);
        setType(new FriendlyTowerType());
        setGraphic(new Graphic(40, 40, "/images/pentagram.png"));
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(10));
        BasicWeapon myWeapon = new BasicWeapon();
        myWeapon.setRange(100);
        myWeapon.setFiringRate(.2);
        myWeapon.setProjectile(new BasicTower());
        setWeapon(myWeapon);
        setMover(new MoverNull());
    }
}
