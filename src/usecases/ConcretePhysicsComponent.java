package usecases;

import engine.gameobject.GameObject;
import engine.gameobject.Tower;
import gameworld.CollisionEngine;
import gameworld.PhysicsComponent;

public class ConcretePhysicsComponent implements PhysicsComponent{
    
    CollisionEngine collisionEngine;
    
    public ConcretePhysicsComponent(CollisionEngine collisionEngine){
        this.collisionEngine = collisionEngine;
    }

    @Override
    public void update (GameObject object) {
        // refer to ConcreteCollisionEngine
        ((Tower) object).getWeapons().forEach(weapon -> {
            collisionEngine.updateInRange(object, weapon.getRange());
        });

        
    }

}
