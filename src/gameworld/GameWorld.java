package gameworld;

import java.util.Collection;
import java.util.List;
import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.pathfinding.Path;


public interface GameWorld extends ObjectCollection {
    public void updateGameObjects ();

    public void addObject (GameObject toSpawn, PointSimple pixelCoords)
                                                                       throws StructurePlacementException;

    public boolean isPlacable (Node n, PointSimple pixelCoords);

}
