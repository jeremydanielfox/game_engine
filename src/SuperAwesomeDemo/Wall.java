package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.weapon.NullWeapon;
import engine.shop.ShopTagSimple;


public class Wall extends GameObjectSimple {
    public Wall () {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setDescription("Block your enemies!");
        shopTag.setShopGraphic(new Graphic(90, 78, "/images/Wall.png"));
        setShopTag(shopTag);
        setLabel(new NeutralGameType());
        Graphic myGraphic = new Graphic(60, 45, "/images/Wall.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(Double.MAX_VALUE));
        setWeapon(new NullWeapon());
        setMover(new MoverNull());
    }
}
