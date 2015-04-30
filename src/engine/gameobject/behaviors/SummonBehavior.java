package engine.gameobject.behaviors;

import java.util.ArrayList;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.Mover;
import gameworld.ObjectCollection;

public class SummonBehavior implements Behavior{
    
    private List<GameObject> mySummons;
    
    public SummonBehavior(){
        mySummons = new ArrayList<GameObject>();
    }

    @Settable
    public void addSummon(GameObject toAdd){
        mySummons.add(toAdd);
    }
    
    public void execute(ObjectCollection world, GameObject object){
        for (GameObject toSummon : mySummons){
            GameObject clone = toSummon.clone();
            Mover newMover = object.getMover().clone();//clone this?
            newMover.setSpeed(toSummon.getMover().getSpeed());
            clone.setMover(newMover);
            clone.setPoint(object.getPoint());
            world.addObject(clone);
        }
    }
}
