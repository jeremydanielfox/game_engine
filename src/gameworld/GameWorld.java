package gameworld;

import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.pathfinding.Path;


public interface GameWorld extends ObjectCollection {
    public void updateGameObjects ();

    public void addObject (GameObject toSpawn, PointSimple pixelCoords)
                                                                       throws StructurePlacementException;

    public boolean isPlaceable (Node n, PointSimple pixelCoords);
    
    public GameObject getObjectFromNode(Node n);
    
    public Path getPath();

}
