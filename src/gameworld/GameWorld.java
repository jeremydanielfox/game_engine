package gameworld;

import java.util.Collection;
import engine.gameobject.GameObject;
import engine.pathfinding.Path;


public interface GameWorld {
    public void updateGameObjects ();
    public void moveObjects();
    public void checkCollisions();
    public void removeDeadObjects();
    public Path getPathFinder ();
    public Collection<GameObject> getGameObjects();
    public void addObject (GameObject object);
}