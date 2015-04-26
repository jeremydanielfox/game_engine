package engine.events;

import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import gameworld.FixedWorld;
import gameworld.GameWorld;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This class outlines the the basic behavior of a wave: the ability to release game objects into a
 * game world over a period of time. It depends on a GameWorld, which it releases game objects into,
 * and it requires a GameObjectQueue which is used to populate the wave with game objects. The wave
 * tracks the enemies that it releases into the world and marks itself as complete once all of the
 * released objects have "died."
 * 
 * @author Sierra
 *
 */
public abstract class Wave extends TimedEvent {

    private static final int DEFAULT_FRAME_TRIGGER = -1;

    private GameWorld myWorld;
    private GameObjectQueue myQueue;
    private Set<GameObject> myWaveSet;

    public Wave () {
        super(DEFAULT_FRAME_TRIGGER);
        initializeVars(new ConcreteQueue(), new FixedWorld());
    }

    public Wave (GameObjectQueue objects, GameWorld world) {
        this(DEFAULT_FRAME_TRIGGER, objects, world);
    }

    public Wave (double startTime, GameObjectQueue objects, GameWorld world) {
        super(startTime);
        initializeVars(objects, world);
    }

    private void initializeVars (GameObjectQueue objects, GameWorld world) {
        myQueue = objects;
        myWorld = world;
        myWaveSet = new HashSet<GameObject>();
    }

    @Settable
    public void setObjectQueue (GameObjectQueue objects) {
        myQueue = objects;
    }

    @Settable
    public void setGameWorld (GameWorld world) {
        myWorld = world;
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
            GameObject gameObject = myQueue.releaseGameObject();
            myWaveSet.add(gameObject);
            myWorld.addObject(gameObject);
            return true;
        }
        return false;
    }
}
