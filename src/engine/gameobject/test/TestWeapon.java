package engine.gameobject.test;

import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.MultipleProjectile;

public class TestWeapon extends BasicWeapon{
    
    public TestWeapon(int type){
        super();
        setProjectile(new TestProjectile(type));
        if(type == 4){
            setFiringStrategy(new MultipleProjectile(8));
            setRange(90);
        }
    }
}
