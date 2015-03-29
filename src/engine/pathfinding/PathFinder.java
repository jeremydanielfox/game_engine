package gameworld;

import gameobject.GameObject;
import javafx.geometry.Point2D;

/**
 * Pathfinder will know where each GameObject can go, since it has access to the current gameWorld. 
 * By giving having separate methods for each type of GameObject, the path can be catered to each. 
 * All enemies will follow the same fixed path and/or the same path-algorithm, and all projectiles
 * will follow their own algorithm to their target. 
 */
public interface PathFinder {
    public Point2D getNextLocation(GameObject o, Point2D current);
}
