package engine.gameobject.weapon.firingstrategy;

import javafx.scene.input.KeyCode;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;

public class UserStrategy extends BasicStrategy {

    private boolean pressedRecently = false;
    private KeyCode myKey;
    
    
    public UserStrategy(){
        
    }
    public UserStrategy(KeyCode myKey){
        
    }
    
    @Override
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype) {
        if (pressedRecently){
            GameObject newProjectile = makeProjectile(location, target.getPoint(), prototype);
        }
    }

}
