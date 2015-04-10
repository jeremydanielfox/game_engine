package engine.gameobject.test;

import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.gameobject.units.BuffableUnit;

public class TestTower extends BuffableUnit{
    
    public TestTower () {
        super();
        myImagePath = "robertDuvall.jpg";
        myLabel = "";
        myHealth = new HealthSimple(3);
        myPoint = new PointSimple(300, 300);
        myMover = new MoverNull();
        myGraphic = new Graphic(100, 100, myImagePath);
        myGraphic.setPoint(myPoint);
        super.setWeapon(new TestWeapon());
    }
    
    
}
