package gameworld;

import java.util.Collection;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;


public interface ObjectCollection {

    public void addObject (GameObject toSpawn);

    public Collection<GameObject> objectsInRange (double range, PointSimple center);

    public Collection<GameObject> getGameObjects ();
}
