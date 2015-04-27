package engine.events;

import engine.gameobject.GameObject;


/**
 * This interface can be implemented by any class that has some collection of game objects that it
 * can "release" one at a time and that it can count. As a result of storing game objects, it
 * depends on the "Game Object" interface. It was designed with the purpose of providing
 * different ways to populate a wave, but could be used for any event or class that needs a
 * collection of game objects that are released one at a time. An example of a way to use this
 * interface can be found by looking at the ConcreteQueue class.
 * 
 * @author Sierra Smith
 *
 */
public interface GameObjectQueue {

    /**
     * Returns "the next" GameObject as determined by the implementation of the class and removes
     * any internal reference to it.
     * 
     * @return a GameObject
     */
    public GameObject releaseGameObject ();

    /**
     * Returns the number of GameObjects left in the queue that can be released.
     * 
     * @return the number of GameObjects left
     */
    public int getObjectCount ();
}
