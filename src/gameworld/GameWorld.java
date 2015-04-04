package gameworld;

import engine.gameobject.GameObject;
import engine.pathfinding.Path;


public interface GameWorld {
    public void updateGameObjects ();
    public Path getPathFinder ();
    public void addObject (GameObject object);
}