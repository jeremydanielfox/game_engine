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
        death.add(new ExplosionBehavior());//On default, explode on death
        endOfPath.add(new DisappearBehavior());
    }
    
    public void addOnDeath(Behavior behavior){
        death.add(behavior);
    }
    
    public void addEndOfPath(Behavior behavior){
        endOfPath.add(behavior);
    }
    
    /**
     * Clear death behaviors
     */
    public void clearDeath(){
        death.clear();
    }
    
    /**
     * Clear end of path behaviors
     */
    public void clearEndOfPath(){
        endOfPath.clear();
    }
    
    public void onEndOfPath(ObjectCollection world, GameObject object){
        executeBehaviors(endOfPath, world, object);
    }
    
    public void onDeath(ObjectCollection world, GameObject object){
        executeBehaviors(death, world, object);
    }
    
    private void executeBehaviors(List<Behavior> behaviors, ObjectCollection world, GameObject object){
        for (Behavior behavior: behaviors){
            System.out.println(behavior.getClass());
            behavior.execute(world, object);
        }
    }
    
}
