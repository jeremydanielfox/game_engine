package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.MoverPoint;
import engine.gameobject.PointSimple;

public abstract class BasicStrategy implements FiringStrategy {
    /**
     * Make a clone of prototype that starts at location, with target as reference point
     * @param location
     * @param target
     * @param prototype
     */
    protected Buffer makeProjectile(PointSimple location, PointSimple target, Buffer prototype){
        MoverPoint newMover = (MoverPoint) prototype.getMover().clone();
        newMover.setPoint(target);
        Buffer newProjectile = (Buffer) prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        return newProjectile;
    }
}
