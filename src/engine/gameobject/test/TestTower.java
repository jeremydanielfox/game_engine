package engine.gameobject.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;

public class TestTower extends GameObjectSimple{
    
    private int type;
    
    public TestTower (int type, int xcor, int ycor) {
        super();
        this.type = type;
        setGraphic(new Graphic(40, 40, "Bloons_TackShooter.png"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        setWeapon(new TestWeapon(type));
        getTag().setName("TestTower");
        getTag().setDescription("Just a test tower; nothing special here...");
        getTag().setShopGraphic(new Graphic(40, 40, "Bloons_TackShooterIcon.png"));
        setLabel(new TowerLabel());
    }
    
}