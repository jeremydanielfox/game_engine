package gameworld;

import gameobject.GameObject;

public interface GameWorld {
	public void updateGameObjects();
	public void addObject(GameObject object);
}
