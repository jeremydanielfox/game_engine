package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import engine.gameobject.movers.MoverNull;
import gameworld.ObjectCollection;

/**
 * Behavior in which the object becomes permanent within the location.
 * Most useful for adding to an end of path behavior.
 * @author PinPinx
 *
 */
public class PlantBehavior implements Behavior{

    public PlantBehavior(){
        
    }
    @Override
    public void execute (ObjectCollection world, GameObject object) {
        object.setMover(new MoverNull());
    }

}
