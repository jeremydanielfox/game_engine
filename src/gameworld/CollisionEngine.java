package gameworld;

import java.util.Collection;

import GameObject.GameObject;

public interface CollisionEngine {
public void detectCollision(GameObject object, Collection<GameObject> objects);
}
