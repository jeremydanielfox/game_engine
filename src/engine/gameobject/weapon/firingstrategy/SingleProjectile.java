package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import engine.pathfinding.Path;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentStraight;
import gameworld.ObjectCollection;


public class SingleProjectile implements FiringStrategy {

    @Override
    public void execute(ObjectCollection world, Buffable target, PointSimple location, Buffer prototype) {
        //TODO: This mover needs to get the right direction somehow...
        //TODO: This mover clone and projectile clone needs to be hashed out
        Mover newMover = prototype.getMover().clone();
        Path newPath = new PathFixed();
        newPath.addPathSegment(new PathSegmentStraight(location, target.getPoint()));
        Projectile newProjectile = (Projectile) prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        world.addObject(newProjectile);
    }
    
}
