package engine.gameobject.test;

import engine.gameobject.DirectPointMover;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.units.DirectDamage;
import engine.gameobject.units.FreezeBuff;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.Projectile;

public class TestProjectile extends Projectile{

    private int myType;
    
    public TestProjectile(int type){
        super();
        myType = type;
        if (type == 1){
            addCollisionBehavior(new DirectDamage(1));
        }
        if (type == 2){
            addCollisionBehavior(new FreezeBuff(20));
        }
        myImagePath = "robertDuvall.jpg";
        myLabel = "";
        myHealth = new HealthSimple(1);
        myPoint = new PointSimple(300,300);
        myMover = new DirectPointMover(myPoint, 2.5);
        myGraphic = new Graphic(20, 20, myImagePath);
        myGraphic.setPoint(myPoint);
    }
    
    public Projectile clone(){
        return new TestProjectile(myType);
    }
}
