package engine.gameobject.behaviors;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

public class SummonBehavior implements Behavior{
    
    private List<GameObject> mySummons;
    
    public SummonBehavior(){
        mySummons = new ArrayList<GameObject>();
    }

    public void addSummon(GameObject toAdd){
        mySummons.add(toAdd);
    }
    
    public void execute(ObjectCollection world, GameObject object){
        for (GameObject toSummon : mySummons){
            toSummon.setMover(object.getMover());
        }
    }
}
