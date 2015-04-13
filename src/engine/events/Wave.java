package engine.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.grid.StructurePlacementException;
import gameworld.GameWorld;


public abstract class Wave extends TimedEvent {

    private GameWorld myWorld;
    private GameObjectQueue myQueue;
    private Set<GameObject> myWaveSet;

    public Wave (GameObjectQueue objects, GameWorld world) {
        this(-1, objects, world);
    }

    public Wave (double startTime, GameObjectQueue objects, GameWorld world) {
        super(startTime);
        myQueue = objects;
        myWorld = world;
        myWaveSet = new HashSet<GameObject>();
    }

    /**
     * This method is to be called during each iteration of the game loop. In concrete wave
     * classes it is responsible for releasing enemies.
     * 
     * @return false if the wave has finished
     */
    @Override
    public boolean update () {
        List<GameObject> list = new ArrayList<GameObject>(myWaveSet);
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).isDead()) {
                myWaveSet.remove(list.get(i));
            }
            i++;
        }
        return !isComplete();
    }

    /**
     * Returns true if all enemies of the wave have been release and have also
     * exited the game world.
     * 
     * @return
     */
    public boolean isComplete () {
        return myWaveSet.isEmpty() && myQueue.getObjectCount() <= 0;
    }

    /**
     * Adds an object to the game world if there are any objects left in the queue.
     * 
     * @return true if an object was released, false if no objects remain
     */
    public boolean releaseObject () {
        if (myQueue.getObjectCount() > 0) {
            // System.out.println("Supposedly adding game object to world.");
            GameObject gameObject = myQueue.releaseGameObject();
            myWaveSet.add(gameObject);
            myWorld.addObject(gameObject);
            return true;
        }
        return false;
    }
}
