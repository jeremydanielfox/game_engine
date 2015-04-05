package engine.events;


import gameworld.GameWorld;


public abstract class Wave extends TimedEvent {

    private GameWorld myWorld;
    private GameObjectQueue myQueue;

    public Wave (GameObjectQueue objects, GameWorld world) {
        this(-1, objects, world);
    }
    
    public Wave (double startTime, GameObjectQueue objects, GameWorld world) {
        super(startTime);
        myQueue = objects;
        myWorld = world;
    }

    /**
     * This method is to be called during each iteration of the game loop. In concrete wave
     * classes it is responsible for releasing enemies.
     * 
     * @return false if the wave has finished
     */
    @Override
    public abstract boolean update ();

    /**
     * Returns a boolean representing whether or not this wave is complete
     * 
     * @return
     */
    public boolean isComplete () {
        return myQueue.getObjectCount() <= 0;
    }

    /**
     * 
     * @return true if an object was released, false if no objects remain
     */
    public boolean releaseObject () {
        if (myQueue.getObjectCount() > 0) {
            myWorld.addObject(myQueue.releaseGameObject());
            return true;
        }
        return false;
    }
}
