package engine.events;

import engine.gameobject.GameObject;


public interface GameObjectQueue {

    public GameObject releaseGameObject ();

    public int getObjectCount ();
}
