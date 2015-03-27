package gameworld;

import gameobject.GameObject;
import java.util.Collection;


public interface CollisionEngine {
    
    public void updateCollisions (GameObject object);
    
    public void updateInRange (GameObject object, double range);
}
