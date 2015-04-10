package engine.gameobject.test;

import engine.gameobject.weapon.BasicWeapon;

public class TestWeapon extends BasicWeapon{
    
    public TestWeapon(){
        super();
        setProjectile(new TestProjectile());
    }
}
