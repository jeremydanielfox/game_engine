package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.shop.tag.GameObjectTag;
import engine.shop.tag.GameObjectTagSimple;

public class TestTower extends GameObjectSimple {

    private int type;

    public TestTower (int type, int xcor, int ycor) {
        super();
        this.type = type;
        Graphic graphic = new Graphic(40, 40, "Bloons_TackShooter.png");
        setGraphic(graphic);
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
//        MoverUser moveruser = new MoverUser();
//        moveruser.setNode(graphic.getNode());
        GameObjectTag tag = null;
        if (type == 0){
            setMover(new MoverNull());
           tag =
                    new GameObjectTagSimple("TestTower", "Just a test tower; nothing special here...",
                                            new Graphic(40, 40, "Bloons_TackShooterIcon.png"), this);
        }
        if (type == 1){
            setMover(new MoverDirection(getPoint(), 1, 200));
            tag =
                    new GameObjectTagSimple("TestTower2", "Just a test tower; nothing special here...",
                                            new Graphic(40, 40, "Bloons_TackShooterIcon.png"), this);
        }
        setWeapon(new TestWeapon(type, this));
        setTag(tag);
        setLabel(new TowerLabel());
    }
    
}
