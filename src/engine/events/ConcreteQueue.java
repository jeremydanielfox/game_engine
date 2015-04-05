package engine.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import engine.gameobject.GameObject;


public class ConcreteQueue implements GameObjectQueue {

    private Queue<GameObject> myOrderedObjects;
    private int curIndex = 0;

    public ConcreteQueue (List<GameObject> objects) {
        populateQueue(objects);
    }

    /**
     * Takes in a total number of game objects and a map of game objects to integers. Populates
     * the list of game objects with the integer percentage of total for the corresponding
     * game object, flooring all percentages of the total.  Then randomizes the order of the list.
     * 
     * @param total
     * @param objectPopulation
     */
    public ConcreteQueue (int total, Map<GameObject, Integer> population) {
        List<GameObject> objects = new ArrayList<GameObject> ();
        for(GameObject o: population.keySet()){
            for(int i = 0; i <= population.get(o); i++){
                objects.add(o.clone());
            }
        }
        Collections.shuffle(objects);
        populateQueue(objects);
    }

    /**
     * Takes a list of game objects and adds them to the queue in order
     * 
     * @param ordered
     */
    private void populateQueue (List<GameObject> ordered) {
        myOrderedObjects = new LinkedList<GameObject>();
        for (GameObject o : ordered) {
            myOrderedObjects.add(o);
        }
    }

    /**
     * Returns the next game object in the queue and removes it from the queue
     */
    @Override
    public GameObject releaseGameObject () {
        return myOrderedObjects.poll();
    }

    /**
     * Reports how many objects are currently in the queue
     */
    @Override
    public int getObjectCount () {
        return myOrderedObjects.size();
    }

}
