package gameworld;

import gameobject.GameObject;

public interface InRangeHandlerTable {
    
    // concrete classes will consult the table to find appropriate lambda function
    // lambda function will be executed, calling relevant "inRange" methods on object and target 
    public void handleInRange(GameObject object, GameObject target);

}
