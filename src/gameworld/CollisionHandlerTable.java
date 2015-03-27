package gameworld;

import GameObject.GameObject;

public interface CollisionHandlerTable {
	public void handleCollision(GameObject object, GameObject otherobject);
}
