package engine.grid;

import java.util.List;
import engine.gameobject.GameObject;

public interface Grid {
        public abstract void removeObject(GameObject o) throws StructurePlacementException;

        public abstract void addObject(GameObject o);

        public abstract void detectRange();
        
        public void detectCollisions();
        
        public List<GameObject> getGameObjects();

}