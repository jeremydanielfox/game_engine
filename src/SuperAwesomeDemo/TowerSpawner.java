package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.gameobject.test.TowerLabel;
import engine.gameobject.weapon.BasicWeapon;
import engine.shop.ShopTagSimple;


public class TowerSpawner extends GameObjectSimple {
    public TowerSpawner () {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setDescription("Master Tower Spawer");
        shopTag.setShopGraphic(new Graphic(40, 40, "/images/pentagram.png"));
        setShopTag(shopTag);
        setLabel(new TowerLabel());
        setGraphic(new Graphic(40, 40, "/images/pentagram.png"));
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        BasicWeapon myWeapon = new BasicWeapon();
        myWeapon.setProjectile(new BasicTower());
        setWeapon(myWeapon);
        setMover(new MoverNull());
    }
}
