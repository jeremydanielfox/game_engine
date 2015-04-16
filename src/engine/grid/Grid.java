package engine.grid;

import java.util.List;

import engine.gameobject.GameObject;
import gameworld.StructurePlacementException;

/**
 * 
 * @author Kaighn
 *
 */
public interface Grid {
        public abstract void removeObject(GameObject o);

        public abstract void addObject(GameObject o) throws StructurePlacementException;

        public abstract void detectRange();
        
        public void detectCollisions();

}