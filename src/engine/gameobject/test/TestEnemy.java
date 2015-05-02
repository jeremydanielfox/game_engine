package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverNull;
import engine.shop.ShopTagSimple;


public class TestEnemy extends GameObjectSimple {
    public TestEnemy () {
        super();
        setGraphic(new Graphic(40, 40, "/images/robertDuvall.jpg"));
        // setPoint();
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        //setWeapon(new TestWeapon(4));
        ShopTagSimple shopTag = new ShopTagSimple();
        setShopTag(shopTag);
        shopTag.setName("TestTower");
        shopTag.setDescription("Just a test tower; nothing special here...");
        shopTag.setShopGraphic(new Graphic(40, 40, "/images/Bloons_TackShooterIcon.png"));
        setType(new EnemyTowerType());
    }
}
