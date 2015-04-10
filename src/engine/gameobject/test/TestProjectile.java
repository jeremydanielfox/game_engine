package engine.gameobject.test;

import engine.gameobject.DirectPointMover;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.units.DirectDamage;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.Projectile;

public class TestProjectile extends Projectile{

    public TestProjectile(){
        super();
        addCollisionBehavior(new DirectDamage(1));
        myImagePath = "robertDuvall.jpg";
        myLabel = "";
        myHealth = new HealthSimple(1);
        myPoint = new PointSimple(300,300);
        myMover = new DirectPointMover(myPoint, 1);
        myGraphic = new Graphic(100, 100, myImagePath);
        myGraphic.setPoint(myPoint);
    }
    
    public Projectile clone(){
        return new TestProjectile();
    }
}
