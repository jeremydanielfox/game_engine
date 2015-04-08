package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import engine.gameobject.GameObject;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import engine.grid.StructurePlacementException;
import gameworld.GameWorld;


public class SingleProjectile implements FiringStrategy {

    @Override
    public void execute (GameWorld world, ArrayList<GameObject> candidates, PointSimple location, Projectile prototype) {
        //TODO: This mover needs to get the right direction somehow...
        Mover newMover = prototype.getMover().clone();
        Projectile newProjectile = (Projectile) prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        try {
            world.addObject(newProjectile);
        }
        catch (StructurePlacementException e) {
            // TODO: hm...This exception is weird.
            e.printStackTrace();
        }
    }
    
}
