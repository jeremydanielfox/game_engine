package gameworld;

import java.util.Collection;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.grid.StructurePlacementException;
import engine.pathfinding.Path;


public interface GameWorld extends ObjectCollection {
    public void updateGameObjects ();

    public void checkCollisions ();

    public void removeDeadObjects ();

    public Path getPathFinder ();
}
