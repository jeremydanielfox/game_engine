package usecases;

import javafx.geometry.Point2D;
import gameobject.Enemy;
import gameworld.GraphicsComponent;
import gameworld.MovementComponent;
import gameworld.PhysicsComponent;


public class ConcreteEnemy implements Enemy {
    
    private MovementComponent mover;
    private PhysicsComponent physics;
    private GraphicsComponent graphics;    

    public ConcreteEnemy(MovementComponent mover, PhysicsComponent physics, GraphicsComponent graphics){
        this.mover = mover;
        this.physics = physics;
        this.graphics = graphics;
    }

    @Override
    public void changeHealth (double amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDead () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updatePosition () {
        mover.update(this);
    }

    @Override
    public void updatePhysics () {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateGraphics () {
        // TODO Auto-generated method stub

    }


    @Override
    public String getImage () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point2D getLocation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onBorn () {
        // TODO Auto-generated method stub
        
    }

}
