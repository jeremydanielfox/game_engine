package usecases;

import gameobject.Tower;
import gameworld.GameWorld;
import gameworld.Grid;

public class ConcreteGameWorld implements GameWorld{
    
    private Grid grid;

    @Override
    // USE CASE 1: a tower detects enemy in range... fires projectile
    public void updateGameObjects () {
        // iterate through all gameObjects and update, in turn, their 
        // position, physics and graphics.
        // for example: a concrete tower
        Tower concreteTower = new ConcreteTower(new ConcreteMovementComponent(), 
                                                new ConcretePhysicsComponent(grid.getCollisionEngine()), 
                                                new ConcreteGraphicsComponent());
        // now updating its physics
        concreteTower.updatePhysics(); 
    }

}
