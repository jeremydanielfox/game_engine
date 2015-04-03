package engine.grid;

import java.util.LinkedList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.Weapon;
import engine.interactions.ConcreteInteractionEngine;
import engine.interactions.InteractionEngine;
import engine.pathfinding.PathFinder;
import gameworld.GameWorld;


public class Grid implements GameWorld {
    private List<GameObject>[][] myGrid;
    private List<GameObject> myGameObjects;
    private InteractionEngine myRangeEngine;
    private InteractionEngine myCollisionEngine;

    @SuppressWarnings("unchecked")
    public Grid (int numRows,
                 int numCols,
                 InteractionEngine rangeEngine,
                 InteractionEngine collisionEngine) {
        myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
        myGameObjects = new LinkedList<>();
        myRangeEngine = rangeEngine;
        myCollisionEngine = collisionEngine;
    }

    /**
     * Allows for a GameObject to be added to the Grid and subsequently the GameWorld
     */
    public void addStructure (int row, int col, GameObject o) {
        myGrid[row][col].add(o);
        myGameObjects.add(o);
    }

    /**
     * Allows for an object to be removed entirely from the Grid and the GameWorld
     *
     * @param o: Object to be removed
     */
    public void removeObject (GameObject o) {

        for (int i = 0; i < myGrid.length; i++)
            for (int j = 0; j < myGrid[0].length; j++)
                if (myGrid[i][j].contains(o))
                    myGrid[i][j].remove(o);
        myGameObjects.remove(o);
    }

    boolean canPlace (int row, int col, GameObject o) {
        // TODO implement. GameObject needs a definition of how many cells it takes up.
        return true;
    }

    public void detectRange () {
        for (GameObject o : myGameObjects) {
            for (Weapon w : o.getWeapons()) {
                myGameObjects.stream()
                        .filter(go -> go.getPoint().withinRange(o.getPoint(), w.getRange()))
                        .forEach(go -> {
                            myRangeEngine.interact(w.getProjectile(), o);
                        });
                // TODO make a weapon not fire upon everything in range. Also, rework
                // weapon/projectile.
            }
        }
    }

    @Override
    public void updateGameObjects () {
        // TODO Auto-generated method stub
        moveAllObjects();
        checkAllCollisions();

    }

    @Override
    public PathFinder getPathFinder () {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Loop through all objects and move each one according to its move method
     */
    private void moveAllObjects () {
        myGameObjects.forEach(go -> go.move());
    }

    /**
     * Loop through all GameObjects and compare them against all other GameObjects, minus
     * themselves.
     * Grab the Nodes from each GameObject -- if the nodes intersect, use the CollisionEngine, which
     * is
     * an InteractionEngine, to execute the appropriate interaction between them.
     */
    private void checkAllCollisions () {
        for (GameObject go1 : myGameObjects)
            for (GameObject go2 : myGameObjects)
                if (go1.getGraphic().getBoundsInParent()
                        .intersects(go2.getGraphic().getBoundsInParent()) && !go1.equals(go2))
                    myCollisionEngine.interact(go1, go2);
        ;
    }
}
