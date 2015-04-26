package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.shop.ShopTagSimple;

public class TestTower extends GameObjectSimple {

    public TestTower (int type, int xcor, int ycor) {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        setShopTag(shopTag);
        shopTag.setName("TestTower");
        shopTag.setDescription("Just a test tower; nothing special here...");
        shopTag.setShopGraphic(new Graphic(40, 40, "Bloons_TackShooterIcon.png"));
        setGraphic(new Graphic(40, 40, "Bloons_TackShooter.png"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
//        MoverUser moveruser = new MoverUser();
//        moveruser.setNode(graphic.getNode());
        setMover(new MoverNull());
        setWeapon(new TestWeapon(type, this));
        setLabel(new TowerLabel());
    }
    
}
