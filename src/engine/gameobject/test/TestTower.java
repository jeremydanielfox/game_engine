package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.shop.tag.GameObjectTag;
import engine.shop.tag.GameObjectTagSimple;


public class TestTower extends GameObjectSimple {

    private int type;

    public TestTower (int type, int xcor, int ycor) {
        super();
        this.type = type;
        setGraphic(new Graphic(40, 40, "Bloons_TackShooter.png"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        setWeapon(new TestWeapon(type));
        GameObjectTag tag =
                new GameObjectTagSimple("TestTower", "Just a test tower; nothing special here...",
                                        new Graphic(40, 40, "Bloons_TackShooterIcon.png"), this);
        setTag(tag);
        setLabel(new TowerLabel());
    }

}
