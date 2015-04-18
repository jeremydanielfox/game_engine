package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.ObjectCollection;


public class SingleProjectile extends BasicStrategy {

    @Override
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype) {
        GameObject newProjectile = makeProjectile(location, target.getPoint(), prototype);
        world.addObject(newProjectile);
    }
    
}
