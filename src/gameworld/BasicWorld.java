package gameworld;

import engine.gameobject.GameObject;
import engine.pathfinding.Path;
import java.util.ArrayList;


public class BasicWorld implements GameWorld {
    ArrayList<GameObject> myObjects;

    public BasicWorld () {
        myObjects = new ArrayList<GameObject>();
    }

    @Override
    public void addObject (GameObject toSpawn) {
        myObjects.add(toSpawn);
    }

    @Override
    public void updateGameObjects () {
        // TODO Auto-generated method stub

    }

    @Override
    public Path getPathFinder () {
        // TODO Auto-generated method stub
        return null;
    }

}