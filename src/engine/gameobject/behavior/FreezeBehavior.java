package engine.gameobject.behavior;

import engine.gameobject.GameObject;

public class FreezeBehavior implements Behavior {
    
    public void apply(GameObject target){
        target.setSpeed(0);
        //target.setGraphics...
    }
}
