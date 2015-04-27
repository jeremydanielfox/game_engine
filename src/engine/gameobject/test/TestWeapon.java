package engine.gameobject.test;

import engine.gameobject.GameObject;
import engine.gameobject.MoverDirection;
import engine.gameobject.test.bloons.Spikes;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.MultipleProjectile;
import engine.gameobject.weapon.upgradetree.TestForest;

public class TestWeapon extends BasicWeapon{
    
    public TestWeapon(int type, GameObject object){
        super();
        setFiringRate(.5);
        setValue(10);
        setTree(new TestForest());
        if (type == 1){
            setProjectile(object);
            setRange(300);
        }
        else {
            setFiringStrategy(new MultipleProjectile(7));
            setProjectile(new TestProjectile(type));
            setRange(60);
        }
        if (type == 2){
            setProjectile(new TestProjectile(type));
            setRange(60);
        }
        if(type == 4){
            setProjectile(object);
            setFiringStrategy(new MultipleProjectile(8));
            setRange(90);
        }
        if (type == 5) {
            GameObject projectile = new TestTower(4, 270, 270);
            projectile.setMover(new MoverDirection(projectile.getPoint(), .2, 90));
            projectile.setWeapon(new TestWeapon(4, object));
            setProjectile(projectile);
        }
    }
}
