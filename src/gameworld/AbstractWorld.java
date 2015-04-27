package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import View.ViewConcrete2;
import voogasalad.util.pathsearch.graph.GridCell;
import javafx.scene.Node;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.test.EnemyLabel;
import engine.gameobject.test.ProjectileLabel;
import engine.gameobject.test.TestTower;
import engine.gameobject.test.TowerLabel;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;
import engine.interactions.InteractionEngine;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;
import engine.pathfinding.Path;
import engine.pathfinding.PathFree;


public class AbstractWorld implements GameWorld {
    private List<GameObject> myObjects;
    private InteractionEngine myCollisionEngine;
    private InteractionEngine myRangeEngine;
    private Map<Node, GameObject> myNodeToGameObjectMap;
    protected Path myPath;
    private Terrain myTerrain;
    protected CoordinateTransformer myTrans;

    public AbstractWorld (int numRows, int numCols) {
        myObjects = new ArrayList<GameObject>();
        initiateCollisionEngine();
        initiateRangeEngine();
        myNodeToGameObjectMap = new HashMap<>();
        myTrans =
                new CoordinateTransformer(numRows, numCols, ViewConcrete2.getWorldWidth(),
                                          ViewConcrete2.getWorldHeight()); // TODO fix window 1000
        myTerrain = new Terrain(numRows, numCols, myTrans);
    }

    @Settable
    public void setObjects (List<GameObject> objects) {
        myObjects = objects;
    }

    /*
     * The private methods that follow is unofficial code:
     * sets up the interaction engines to defaults. Set interaction engine methods may be needed.
     */

    private void initiateCollisionEngine () {
        myCollisionEngine = new CollisionEngine();
        myCollisionEngine.setWorld(this);
        myCollisionEngine.put(new ProjectileLabel(), new EnemyLabel(), new BuffImparter());
    }

    private void initiateRangeEngine () {
        myRangeEngine = new RangeEngine();
        myRangeEngine.setWorld(this);
        myRangeEngine.put(new TowerLabel(), new EnemyLabel(), new ShootAt());
    }

    @Override
    public void addObject (GameObject toSpawn, PointSimple pixelCoords)
                                                                       throws StructurePlacementException {
        myObjects.add(toSpawn);
        toSpawn.setPoint(pixelCoords);
        myNodeToGameObjectMap.put(toSpawn.getGraphic().getNode(), toSpawn);
        // myGrid.addObject(toSpawn);
    }

    @Override
    public void updateGameObjects () {
        ArrayList<GameObject> currentObjects = new ArrayList<GameObject>(myObjects);
        for (GameObject object : currentObjects) {
            object.update(this);
            for (GameObject interactObject : currentObjects) {
                if (interactObject != object && !object.isDead() && !interactObject.isDead()) {
                    myCollisionEngine.interact(object, interactObject);
                    myRangeEngine.interact(object, interactObject);
                }
            }
        }
        removeDeadObjects();
    }

    private void removeDeadObjects () {
        ArrayList<GameObject> buffer = new ArrayList<GameObject>();
        myObjects.forEach(go -> {
            if (go.isDead()) {
                buffer.add(go);
            }
        });
        for (GameObject toRemove : buffer) {
            myObjects.remove(toRemove);
            toRemove.onDeath(this);
        }
    }

    @Override
    public Collection<GameObject> getGameObjects () {
        return Collections.unmodifiableList(myObjects);
    }

    @Override
    public Collection<GameObject> objectsInRange (double range, PointSimple center) {
        ArrayList<GameObject> inRange = new ArrayList<>();
        for (GameObject o : myObjects) {
            if (center.withinRange(o.getPoint(), range)) {
                inRange.add(o);
            }
        }
        return Collections.unmodifiableList(inRange);
    }

    @Override
    public void addObject (GameObject toSpawn) {
        myObjects.add(toSpawn);
        myNodeToGameObjectMap.put(toSpawn.getGraphic().getNode(), toSpawn);
    }

    @Override
    public boolean isPlaceable (Node n, PointSimple pixelCoords) {
        GridCell c = myTrans.transformWorldToGrid(pixelCoords);
        try {
            return myTerrain.getTerrainTile(c).getPlace();
        }
        catch (InvalidArgumentException e) {
            return false;
        }
    }

    @Override
    public GameObject getObjectFromNode (Node n) {
        return myNodeToGameObjectMap.get(n);
    }

    @Settable
    public void setPath (Path p) {
        myPath = p;
    }

    @Override
    public Path getPath () {
        return myPath;
    }

    @Settable
    public void setObstacles (List<GridCell> obstacles) {
        for (GridCell c : obstacles) {
            try {
                myTerrain.getTerrainTile(c).setWalk(false);
            }
            catch (InvalidArgumentException e) {
            }
        }
        PathFree path = (PathFree) myPath;
        path.setObstacles(obstacles);
    }

    public void setTowerObstacles (List<GridCell> tObstacles) {
        for (GridCell c : tObstacles) {
            try {
                myTerrain.getTerrainTile(c).setPlace(false);
            }
            catch (InvalidArgumentException e) {
            }
        }
    }

    @Override
    public void removeObject (GameObject toRemove) {
        myObjects.remove(toRemove);

    }

    @Settable
    public void setRangeEngine (InteractionEngine engine) {
        this.myRangeEngine = engine;
    }

    @Settable
    public void setCollisionEngine (InteractionEngine engine) {
        this.myCollisionEngine = engine;

    }
}
