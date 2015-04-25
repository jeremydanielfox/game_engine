package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.MoverPoint;
import engine.gameobject.PointSimple;


public abstract class BasicStrategy implements FiringStrategy {
    /**
     * Make a clone of prototype that starts at location, with target as reference point
     * 
     * @param location
     * @param target
     * @param prototype
     */
    protected GameObject makeProjectile (PointSimple location,
                                         PointSimple target,
                                         GameObject prototype) {
        //TODO: Conversion for non moverpoints.
        MoverPoint newMover = (MoverPoint) prototype.getMover().clone();
        newMover.setPoint(target);
        GameObject newProjectile = prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        return newProjectile;
    }
}
