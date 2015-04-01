package usecases;

import engine.gameobject.GameObject;
import gameworld.CollisionEngine;
import gameworld.CollisionHandlerTable;
import gameworld.GameWorld;
import gameworld.InRangeHandlerTable;
import java.util.ArrayList;
import java.util.Collection;

public class ConcreteCollisionEngine implements CollisionEngine{

    private GameWorld gameWorld;
    private CollisionHandlerTable collisionTable;
    private InRangeHandlerTable rangeTable;
    
    @Override
    public void updateCollisions (GameObject object) {
        handleCollisions(object, detectCollisions(object));
    }
    
    @Override
    public void updateInRange (GameObject object, double range) {
        handleInRange(object, detectInRange(object, range));
    }

    private Collection<GameObject> detectCollisions(GameObject object){
        return detectInRange(object, 0);
    }
    
    private Collection<GameObject> detectInRange(GameObject object, double range){
        Collection<GameObject> result = new ArrayList<GameObject>();
        // use the gameWorld to iterate through all other gameObjects
        // if "in range" is detected (via intersection of bounding boxes), 
        // add to the returned collection of gameObjects
        return result;
    }
    
    private void handleCollisions (GameObject collider, Collection<GameObject> collidees) {
    }
    

    private void handleInRange (GameObject object, Collection<GameObject> targets) {
        targets.forEach(target-> rangeTable.handleInRange(object, target));
        
    }

}
