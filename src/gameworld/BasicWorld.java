package gameworld;

import java.util.ArrayList;
import engine.gameobject.GameObject;
import engine.pathfinding.PathFinder;


public class BasicWorld implements GameWorld {
    ArrayList<GameObject> myObjects;

    public BasicWorld () {
        myObjects = new ArrayList<GameObject>();
    }

    @Override
    public void updateGameObjects () {
        // TODO Auto-generated method stub

    }

    @Override
    public PathFinder getPathFinder () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addStructure (int row, int col, engine.gameobject.GameObject object) {
        myObjects.add(object);
        // TODO Auto-generated method stub

    }

}
