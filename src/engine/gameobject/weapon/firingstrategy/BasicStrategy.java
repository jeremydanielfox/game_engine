package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import engine.gameobject.MoverNull;
import engine.gameobject.MoverPoint;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;

public abstract class BasicStrategy implements FiringStrategy {
    /**
     * Make a clone of prototype that starts at location, with target as reference point
     * @param location
     * @param target
     * @param prototype
     */
    protected GameObject makeProjectile(PointSimple location, PointSimple target, GameObject prototype){
        MoverPoint newMover = (MoverPoint) prototype.getMover().clone();
        newMover.setPoint(target);
        GameObject newProjectile = (GameObject) prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        return newProjectile;
    }
}
