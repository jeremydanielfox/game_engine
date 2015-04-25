package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;
import java.util.ArrayList;
import java.util.List;

public class BehaviorTracker {
    private List<Behavior> endOfPath;
    private List<Behavior> death;
    
    public BehaviorTracker(){
        endOfPath = new ArrayList<Behavior>();
        death = new ArrayList<Behavior>();
    }
    
    public void onEndOfPath(ObjectCollection world, GameObject object){
        executeBehaviors(endOfPath, world, object);
    }
    public void onDeath(ObjectCollection world, GameObject object){
        executeBehaviors(death, world, object);
        object.explode(world);//All objects explode when they die (possibly a null explosion)
    }
    
    private void executeBehaviors(List<Behavior> behaviors, ObjectCollection world, GameObject object){
        for (Behavior behavior: behaviors){
            behavior.execute(world, object);
        }
    }
}
