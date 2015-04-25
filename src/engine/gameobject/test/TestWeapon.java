package engine.gameobject.test;

import engine.gameobject.GameObject;
import engine.gameobject.MoverDirection;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.firingstrategy.MultipleProjectile;
import engine.gameobject.weapon.upgradetree.TestTree;


public class TestWeapon extends BasicWeapon {

    public TestWeapon (int type) {
        super();
        setProjectile(new TestProjectile(type));
        setTree(new TestTree());

        if (type == 4) {
            setFiringStrategy(new MultipleProjectile(8));
            setRange(90);
        }
        if (type == 5) {
            GameObject projectile = new TestTower(4, 270, 270);
            projectile.setMover(new MoverDirection(projectile.getPoint(), .2, 90));
            projectile.setWeapon(new TestWeapon(4));
            setProjectile(projectile);
        }
    }
}
