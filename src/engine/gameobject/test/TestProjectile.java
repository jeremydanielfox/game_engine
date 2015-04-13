package engine.gameobject.test;

import engine.gameobject.DirectPointMover;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
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

        
        myImagePath = "robertDuvall.jpg";
        myLabel = "";
        myHealth = new HealthSimple(1);
        myPoint = new PointSimple(300,300);
        if (type == 1 || type == 4){
            addCollisionBehavior(new DirectDamage(1));
        }
        if (type == 2){
            addCollisionBehavior(new FreezeBuff(30));
        }
        if (type == 3){
            addOnDeath(new FreezeBuff(30));
            setOnDeathRadius(100);
            myHealth = new HealthSimple(0);
        }
        myMover = new MoverDirection(myPoint, 2.5, 250);
        if (type == 4){
            addCollisionBehavior(new DirectDamage(1));
            myMover = new MoverDirection(myPoint, 4, 90);
        }
        myGraphic = new Graphic(20, 20, myImagePath);
        myGraphic.setPoint(myPoint);
    }
    
    public Projectile clone(){
        return new TestProjectile(myType);
    }
}
