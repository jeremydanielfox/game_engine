package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import engine.pathfinding.Path;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentStraight;


public class SingleProjectile implements FiringStrategy {

    @Override
    public List<Buffer> execute(List<Buffable> targets, PointSimple location, Buffer prototype) {
        //TODO: This mover needs to get the right direction somehow...
        List<Buffer> projectile = new ArrayList<Buffer>();
        //TODO: The target choice can't be so arbitrary later...
        Buffable myTarget = targets.get(0);
        //TODO: This mover clone and projectile clone needs to be hashed out
        Mover newMover = prototype.getMover().clone();
        Path newPath = new PathFixed();
        newPath.addPathSegment(new PathSegmentStraight(location, myTarget.getPoint()));
        Projectile newProjectile = (Projectile) prototype.clone();
        newProjectile.setPoint(location);
        newProjectile.setMover(newMover);
        projectile.add(newProjectile);
        return projectile;
    }
    
}
