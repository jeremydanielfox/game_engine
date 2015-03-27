package usecases;

import javafx.geometry.Point2D;
import gameobject.Enemy;


public class BasicEnemy implements Enemy {

    private int id;

    public BasicEnemy (int id) {
        this.id = id;
    }

    @Override
    public void changeHealth (double amount) {

    }

    public void onBorn () {
        System.out.println("Enemy id # " + id + " spawned!!");
    }

    @Override
    public boolean isDead () {
        return false;
    }

    @Override
    public void updatePhysics () {

    }

    @Override
    public void updateGraphics () {

    }


    @Override
    public void updatePosition () {
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

}
