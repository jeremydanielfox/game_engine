package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.ObjectCollection;


public class SingleProjectile extends BasicStrategy {

    @Override
    public void execute(ObjectCollection world, Buffable target, PointSimple location, Buffer prototype) {
        Buffer newProjectile = makeProjectile(location, target.getPoint(), prototype);
        world.addObject(newProjectile);
    }
    
}
