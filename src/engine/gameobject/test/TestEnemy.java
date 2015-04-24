package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;


public class TestEnemy extends GameObjectSimple {
    public TestEnemy () {
        super();
        setGraphic(new Graphic(40, 40, "robertDuvall.jpg"));
//        setPoint();
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        //setWeapon(new TestWeapon(4));
        getTag().setName("TestTower");
        getTag().setDescription("Just a test tower; nothing special here...");
        getTag().setShopGraphic(new Graphic(40, 40, "Bloons_TackShooterIcon.png"));
        setLabel(new EnemyLabel());
    }
}
