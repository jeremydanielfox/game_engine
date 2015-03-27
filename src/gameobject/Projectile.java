package gameobject;

import javafx.geometry.Point2D;


public interface Projectile extends Mortal {
    
    public void attack(GameObject gameObject);
    
    public Point2D getTarget();

}