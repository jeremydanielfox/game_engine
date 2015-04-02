package gameworld;

import engine.gameobject.GameObject;
import engine.pathfinding.PathFinder;


public interface GameWorld {
    public void updateGameObjects ();
    public PathFinder getPathFinder ();
    public void addObject (GameObject object);
}
