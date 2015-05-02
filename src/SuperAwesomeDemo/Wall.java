package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverNull;
import engine.gameobject.weapon.NullWeapon;
import engine.shop.ShopTagSimple;


public class Wall extends GameObjectSimple {
    public Wall () {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setName("Wall");
        shopTag.setDescription("Block your enemies!");
        shopTag.setShopGraphic(new Graphic(90, 78, "/images/Wall.png"));
        setShopTag(shopTag);
        setType(new NeutralGameType());
        Graphic myGraphic = new Graphic(60, 45, "/images/Wall.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(Double.MAX_VALUE));
        setWeapon(new NullWeapon());
        setMover(new MoverNull());
    }
}
