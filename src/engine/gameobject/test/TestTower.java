package engine.gameobject.test;

import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.gameobject.units.BuffableUnit;

public class TestTower extends BuffableUnit{
    
    public TestTower (int type, int xcor, int ycor) {
        super();
        setGraphic(new Graphic(40, 40, "robertDuvall.jpg"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        setWeapon(new TestWeapon(type));
    }
    
    
}
