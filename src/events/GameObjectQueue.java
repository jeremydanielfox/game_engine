package events;

import gameobject.GameObject;


public interface GameObjectQueue {

    public GameObject releaseGameObject ();

    public int getObjectCount ();
}
