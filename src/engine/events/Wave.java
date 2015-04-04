<<<<<<< HEAD:src/engine/events/Wave.java
package engine.events;
=======

package events;
>>>>>>> 4ef3523c8a38eb88ab9b13cadf3d88adf0c1dea0:src/events/Wave.java

import gameworld.GameWorld;


public abstract class Wave extends TimedEvent {

    private GameWorld myWorld;
    private GameObjectQueue myQueue;

    public Wave (GameObjectQueue objects, GameWorld world) {
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
    public abstract boolean update (int frameCount);

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
