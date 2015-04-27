package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.Mover;
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
        Mover newMover = prototype.getMover().clone();
        try {
            ((MoverPoint) newMover).setPoint(target);
        }
        catch (Exception e){
            //Do nothing
        }
        GameObject newProjectile = prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        return newProjectile;
    }
}
