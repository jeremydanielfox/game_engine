package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import engine.gameobject.HealthSimple;
import gameworld.ObjectCollection;

public class DeathBehavior implements Behavior{

    public DeathBehavior(){
        
    }
    @Override
    public void execute (ObjectCollection world, GameObject object) {
        object.setHealth(new HealthSimple(0));//Kills object
    }
}
