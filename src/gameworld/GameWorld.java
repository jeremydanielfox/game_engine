package gameworld;

import engine.pathfinding.PathFinder;
import gameobject.GameObject;


public interface GameWorld {
    public void updateGameObjects ();
    public PathFinder getPathFinder ();
    public void addObject (GameObject object);
}
