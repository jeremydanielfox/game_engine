package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.behaviors.PlantBehavior;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverDirection;
import engine.gameobject.movers.MoverNull;
import engine.shop.ShopTagSimple;


public class TestTower extends GameObjectSimple {

    public TestTower (int type, int xcor, int ycor) {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        setShopTag(shopTag);
        shopTag.setDescription("Just a test tower; nothing special here...");
        shopTag.setShopGraphic(new Graphic(40, 40, "/images/Bloons_TackShooterIcon.png"));
        setGraphic(new Graphic(40, 40, "/images/Bloons_TackShooter.png"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        clearEndOfPathBehavior();
        addEndOfPathBehavior(new PlantBehavior());
        // MoverUser moveruser = new MoverUser();
        // moveruser.setNode(graphic.getNode());
        if (type == 0) {
            setMover(new MoverNull());
            shopTag.setName("TestTower"+Math.random());
        }
        if (type == 1) {
            setMover(new MoverDirection(getPoint(), 1, 200));
            shopTag.setName("TestTower2");
        }
        setWeapon(new TestWeapon(type, this));
        setType(new FriendlyTowerType());
    }

}