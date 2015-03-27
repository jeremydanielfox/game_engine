package gameworld;

import java.util.Collection;

import GameObject.GameObject;

public interface InRangeEngine {
public void detectInRange(GameObject object, int range, Collection<GameObject> objects);
}
