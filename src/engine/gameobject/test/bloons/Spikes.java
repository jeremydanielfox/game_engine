package engine.gameobject.test.bloons;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.test.ProjectileLabel;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.shop.ShopTagSimple;


public class Spikes extends GameObjectSimple {
    public Spikes () {
        super();
        setLabel(new ProjectileLabel());
        setGraphic(new Graphic(20, 20, "spikes.jpg"));
        this.getCollider().addCollisionBehavior(new DamageBuff(1));
        setPoint(new PointSimple(0, 10000)); 
        setHealth(new HealthSimple(10));
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setName("Spikies");
        shopTag.setShopGraphic(new Graphic(40, 40, "spikes.jpg"));
        shopTag.setDescription("Spikes!");
        setShopTag(shopTag);
    }
}
