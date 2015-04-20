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
    
    public TestTower (int type, int xcor, int ycor) {
        super();
        setGraphic(new Graphic(40, 40, "Bloons_TackShooter.png"));
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        setMover(new MoverNull());
        setWeapon(new TestWeapon(4));
        getTag().setName("TestTower");
        getTag().setDescription("Just a test tower; nothing special here...");
        getTag().setShopGraphic(new Graphic(40, 40, "Bloons_TackShooterIcon.png"));
        setLabel(new TowerLabel());
    }
    
//    @Override
//    public void fire (ObjectCollection world, GameObject target) {
//        TestTower projectile =
//                new TestTower(3, (int) super.getPoint().getX(), (int) super.getPoint().getY());
//        List<Double> places = new ArrayList<>();
//        for (double i = -20; i < 21; i++) {
//            places.add(i);
//        }
//        Collections.shuffle(places);
//        projectile
//                .setMover(new MoverDirection(new PointSimple(places.get(0), places.get(1)), 1, 30));
//        super.getWeapon().setProjectile(projectile);
//        super.getWeapon().fire(world, target, super.getPoint());
//    }
//    
    
}