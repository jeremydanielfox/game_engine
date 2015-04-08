package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import gameworld.GameWorld;


public class MultipleProjectile implements FiringStrategy {
    
    private int projectilesCreated;
    
    public MultipleProjectile(int numProjectiles){
        projectilesCreated = numProjectiles;
    }
    

    @Override
    public void execute (GameWorld world,
                         ArrayList<GameObject> candidate,
                         PointSimple location,
                         Projectile prototype) {
        
        
    }

}
