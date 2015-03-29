package events;

import gameworld.GameWorld;


public abstract class Wave {

    private GameWorld myWorld;
    private GameObjectQueue myQueue;

    public Wave (GameObjectQueue objects, GameWorld world) {
        myQueue = objects;
        myWorld = world;
    }

    /**
     * This method is to be called during each iteration of the game loop. In concrete wave
     * classes it is responsible for releasing enemies.
     */
    public abstract void update ();

    /**
     * Returns a boolean representing whether or not this wave is complete
     * 
     * @return
     */
    public boolean isComplete () {
        return myQueue.getObjectCount() <= 0;
    }

    public void releaseObject () {
        if (myQueue.getObjectCount() > 0)
            myWorld.addObject(myQueue.releaseGameObject());
    }
}
