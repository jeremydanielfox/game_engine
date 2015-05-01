package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverUser;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.shop.ShopTagSimple;


public class Hero extends GameObjectSimple {
    public Hero (int xcor, int ycor) {
        super();
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setDescription("Another hero?");
        shopTag.setShopGraphic(new Graphic(47, 28, "/images/BoxheadHero.png"));
        setShopTag(shopTag);
        setLabel(new HeroType());
        Graphic myGraphic = new Graphic(47, 28, "/images/BoxheadHero.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(500000));
//        Weapon myWeapon = new BasicWeapon();
//        myWeapon.setProjectile(new BasicFriendlyProjectile());
//        myWeapon.setFiringRate(.5);
//        myWeapon.setRange(100);
//        setWeapon(new TowerShooter(this));
        setWeapon(new Shotgun(this));
        MoverUser myMover = new MoverUser(1);
        myMover.setGraphic(myGraphic);
        // myMover.setRange(200);
        setMover(myMover);
        // clearEndOfPathBehavior();
        // this.addEndOfPathBehavior(new PlantBehavior());
    }
}
