package gameworld;

import engine.gameobject.GameObject;

public interface CollisionHandlerTable {
    
        // concrete classes will consult the table to find appropriate lambda function
        // lambda function will be executed, calling relevant "onCollision" methods on object and other 
	public void handleCollision(GameObject object, GameObject other);
}
