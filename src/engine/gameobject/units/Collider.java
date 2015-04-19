package engine.gameobject.units;

import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.firingstrategy.Explosion;
import gae.listView.DeepCopy;
import gameworld.ObjectCollection;

public class Collider {
    private Set<String> collidedID;
    private Set<Buff> onCollision;
    private Explosion onDeath;
    
    public Collider () {
        collidedID = new HashSet<String>();
        onCollision = new HashSet<Buff>();
        onDeath = new Explosion();
    }

    public void addCollisionBehavior(Buff newBuff){
        removeDuplicateBuff(newBuff);
        onCollision.add(newBuff);
    }
 
    public void setExplosionRadius(double radius){
        onDeath.setRadius(radius);
    }
    
    public void addExplosionBuff(Buff newBuff){
        onDeath.addBuff(newBuff);;
    }
    
    public void collide (GameObject target) {
        onCollision(target);
        //TODO: Keep track of things that have been collided using something like
        //collidedID.add(target.getName());
    }
    
    public void explode (ObjectCollection world, PointSimple location){
        onDeath.explode(world, location);
    }
    
    private void removeDuplicateBuff(Buff newBuff){
        for (Buff b: onCollision){
            if (b.getClass().equals(newBuff.getClass())){
                onCollision.remove(b);
            }
        }
    }
    
    private void onCollision (GameObject target) {
        for (Buff b : onCollision) {
            target.addBuff(b);
        }
    }
    
    
    /**
     * Returns the deep clone, but must test to see if behavior is correct
     */
    public Collider clone(){
        return (Collider) DeepCopy.copy(this);
    }

}
